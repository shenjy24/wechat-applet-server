package com.jonas.service;

import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.SystemCode;
import com.jonas.repository.mysql.dao.WechatSecretDao;
import com.jonas.repository.mysql.dao.WechatUserDao;
import com.jonas.repository.mysql.dao.WechatUserInfoDao;
import com.jonas.repository.mysql.entity.WechatSecret;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.repository.mysql.entity.WechatUserInfo;
import com.jonas.service.dto.Code2SessionResponse;
import com.jonas.service.dto.UserProfile;
import com.jonas.service.dto.UserView;
import com.jonas.util.GsonUtil;
import com.jonas.util.OkHttpUtil;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/6/24 17:22
 * @description LoginService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${applet.appid}")
    private String appid;
    @Value("${applet.code2sessionUrl}")
    private String code2sessionUrl;

    private final WechatSecretDao wechatSecretDao;
    private final WechatUserDao wechatUserDao;
    private final WechatUserInfoDao wechatUserInfoDao;

    private final UserService userService;

    /**
     * 微信小程序登录
     * 接口文档：<a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html">...</a>
     *
     * @param code 微信小程序临时登录凭证
     * @return session信息
     */
    public WechatUser code2session(String code) {
        WechatSecret wechatSecret = wechatSecretDao.findById(appid).orElse(null);
        if (null == wechatSecret) {
            log.error("WechatSecret不存在，appid为{}", appid);
            throw new BizException(SystemCode.BIZ_ERROR);
        }

        Map<String, Object> args = new HashMap<String, Object>() {{
            put("appid", appid);
            put("secret", wechatSecret.getSecret());
            put("js_code", code);
            put("grant_type", "authorization_code");
        }};
        log.info("调用code2session开始，appid为{}, js_code为{}", appid, code);
        try {
            Code2SessionResponse response = OkHttpUtil.synGet(code2sessionUrl, args, Code2SessionResponse.class);
            if (null == response) {
                log.error("调用code2session失败，返回值为空");
                throw new BizException(SystemCode.BIZ_ERROR);
            }
            log.info("调用code2session成功，返回值为{}", response);
            return userService.saveOrUpdateWechatUser(response.getOpenid(), response.getUnionid(), response.getSession_key());
        } catch (IOException | JOSEException e) {
            log.error("调用code2session异常", e);
            throw new BizException(SystemCode.BIZ_ERROR);
        }
    }

    public UserProfile decryptUserProfile(WechatUser wechatUser, String rawData, String signature,
                                          String encryptedData, String iv) {
        if (null == wechatUser) {
            log.error("[decryptUserProfile] wechatUser不存在");
            throw new BizException(SystemCode.BIZ_ERROR);
        }

        if (StringUtils.isBlank(wechatUser.getSessionKey())) {
            log.error("[decryptUserProfile] sessionKey不存在，openid为{}", wechatUser.getOpenid());
            throw new BizException(SystemCode.BIZ_ERROR);
        }

        // 验证签名
        if (!this.checkSignature(rawData, wechatUser.getSessionKey(), signature)) {
            log.error("[decryptUserProfile] 验签失败，rawData为{}，sessionKey为{}，signature为{}",
                    rawData, wechatUser.getSessionKey(), signature);
            throw new BizException(SystemCode.BIZ_ERROR);
        }

        String decryptedData = this.decryptWxData(encryptedData, wechatUser.getSessionKey(), iv);
        if (StringUtils.isBlank(decryptedData)) {
            log.error("[decryptUserProfile] 解码失败，encryptedData为{}，sessionKey为{}，iv为{}",
                    encryptedData, wechatUser.getSessionKey(), iv);
            throw new BizException(SystemCode.BIZ_ERROR);
        }
        // 校验水印
        UserProfile userProfile = GsonUtil.toBean(decryptedData, UserProfile.class);
        if (null == userProfile || null == userProfile.getWatermark()
                || !userProfile.getWatermark().getAppid().equals(appid)) {
            log.error("[decryptUserProfile] 水印异常");
            throw new BizException(SystemCode.BIZ_ERROR);
        }
        return userProfile;
    }

    /**
     * 验签
     *
     * @param rawData    明文
     * @param sessionKey 会话密钥
     * @param signature  签名
     * @return 是否验签成功
     */
    private boolean checkSignature(String rawData, String sessionKey, String signature) {
        String content = rawData + sessionKey;
        String sign = DigestUtils.shaHex(content);
        return sign.equals(signature);
    }

    /**
     * 解密微信加密数据，对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">...</a>
     *
     * @param encryptedData 加密串
     * @param sessionKey    会话密钥
     * @param iv            解密算法初始向量
     * @return 解密后的数据
     */
    private String decryptWxData(String encryptedData, String sessionKey, String iv) {
        try {
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            SecretKeySpec spec = new SecretKeySpec(Base64.decodeBase64(sessionKey.getBytes(StandardCharsets.UTF_8)), "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(Base64.decodeBase64(iv.getBytes(StandardCharsets.UTF_8))));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(Base64.decodeBase64(encryptedData.getBytes(StandardCharsets.UTF_8)));
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, StandardCharsets.UTF_8);
                log.info("微信加密数据解析结果: {}", result);
                return result;
            }
        } catch (Exception e) {
            log.error("微信加密数据解析失败", e);
        }
        return "";
    }

    public WechatUserInfo updateUserProfile(WechatUser wechatUser, String avatar, String nickname) {
        if (null == wechatUser) {
            log.error("[updateUserProfile] wechatUser不存在");
            throw new BizException(SystemCode.BIZ_ERROR);
        }
        WechatUserInfo userInfo = wechatUserInfoDao.findById(wechatUser.getOpenid()).orElse(null);
        if (null == userInfo) {
            log.error("[updateUserProfile] userInfo不存在");
            throw new BizException(SystemCode.BIZ_ERROR);
        }
        userInfo.setAvatar(avatar);
        userInfo.setNickname(nickname);
        userInfo.setUpdateTime(LocalDateTime.now());
        log.info("更新用户信息成功，avatarUrl:{}, nickname:{}", avatar, nickname);
        return wechatUserInfoDao.save(userInfo);
    }

    public UserView buildUserView(WechatUserInfo userInfo) {
        return new UserView(userInfo.getAvatar(), userInfo.getNickname());
    }
}

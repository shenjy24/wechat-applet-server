package com.jonas.service;

import com.jonas.common.Constant;
import com.jonas.common.NameUtil;
import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.SystemCode;
import com.jonas.repository.mysql.dao.WechatUserDao;
import com.jonas.repository.mysql.dao.WechatUserInfoDao;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.repository.mysql.entity.WechatUserInfo;
import com.jonas.service.dto.UserView;
import com.jonas.util.JwtUtil;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final WechatUserDao wechatUserDao;
    private final WechatUserInfoDao wechatUserInfoDao;

    public WechatUser saveOrUpdateWechatUser(String openid, String unionid, String sessionKey) {
        WechatUser wechatUser = wechatUserDao.findById(openid).orElse(null);
        if (wechatUser == null) {
            String token = this.getToken(openid);
            wechatUser = new WechatUser(openid, unionid, sessionKey, token);
            wechatUserDao.save(wechatUser);
            log.info("保存WechatUser成功，{}", wechatUser);

            WechatUserInfo userInfo = new WechatUserInfo(openid, unionid, Constant.AVATAR_DEFAULT, NameUtil.getName(2));
            wechatUserInfoDao.save(userInfo);
            log.info("保存WechatUserInfo成功，{}", userInfo);
            return wechatUser;
        } else {
            String oldSessionKey = wechatUser.getSessionKey();
            String oldToken = wechatUser.getToken();
            if (!oldSessionKey.equals(sessionKey)) {
                String token = this.getToken(openid);
                wechatUser.setToken(token);
                wechatUser.setSessionKey(sessionKey);
                log.info("更新WechatUser成功，旧sessionKey为{}，新sessionKey为{}, 旧token为{}，新token为{}",
                        oldSessionKey, sessionKey, oldToken, token);
                return wechatUserDao.save(wechatUser);
            }
        }
        return wechatUser;
    }

    private String getToken(String openid) {
        try {
            return JwtUtil.generateToken(openid);
        } catch (JOSEException e) {
            log.error("保存或创建微信用户异常", e);
            throw new BizException(SystemCode.SERVER_ERROR);
        }
    }

    public WechatUserInfo getWechatUserInfo(String openid) {
        return wechatUserInfoDao.findById(openid).orElse(null);
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

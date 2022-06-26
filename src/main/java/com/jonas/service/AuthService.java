package com.jonas.service;

import com.jonas.repository.mysql.dao.WechatSecretDao;
import com.jonas.repository.mysql.dao.WechatUserDao;
import com.jonas.repository.mysql.entity.WechatSecret;
import com.jonas.service.dto.Code2SessionResponse;
import com.jonas.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/6/24 17:22
 * @description LoginService
 */
@Slf4j
@Service
public class AuthService {

    @Value("${applet.appid}")
    private String appid;
    @Value("${applet.code2sessionUrl}")
    private String code2sessionUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WechatSecretDao wechatSecretDao;
    @Autowired
    private WechatUserDao wechatUserDao;

    /**
     * 微信小程序登录
     * 接口文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     *
     * @param code 微信小程序临时登录凭证
     * @return session信息
     */
    public Code2SessionResponse code2session(String code) {
        WechatSecret wechatSecret = wechatSecretDao.getById(appid);
        if (null == wechatSecret) {
            log.error("不存在的appid:{}", appid);
            return null;
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
            log.info("调用code2session成功，返回值为{}", response);
            wechatUserDao.saveOrUpdateWechatUser(response.getOpenid(), response.getUnionid(), response.getSession_key());
            return response;
        } catch (IOException e) {
            log.error("调用code2session异常", e);
            return null;
        }
    }
}

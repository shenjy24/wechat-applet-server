package com.jonas.service;

import com.jonas.data.mysql.dao.IWechatSecretService;
import com.jonas.data.mysql.entity.WechatSecret;
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
    private IWechatSecretService wechatSecretService;

    /**
     * 微信小程序登录
     *
     * @param code 微信小程序临时登录凭证
     * @return session信息
     */
    public Code2SessionResponse code2session(String code) {
        WechatSecret wechatSecret = wechatSecretService.getById(appid);
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
            return response;
        } catch (IOException e) {
            log.error("调用code2session异常，返回值为{}", e);
            return null;
        }
    }
}

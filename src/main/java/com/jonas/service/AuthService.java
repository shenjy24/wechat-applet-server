package com.jonas.service;

import com.jonas.service.dto.Code2SessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/6/24 17:22
 * @description LoginService
 */
@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String code2sessionUrl = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 微信小程序登录
     *
     * @param code 微信小程序临时登录凭证
     * @return 用户唯一标识 OpenID
     */
    public Code2SessionResponse code2session(String code) {
        Map<String, Object> args = new HashMap<String, Object>() {{
           put("appid", "wx9692a6329bcdb403");
           put("secret", "f4a63407b101e6f51e4d70e58476387c");
           put("js_code", code);
           put("grant_type", "authorization_code");
        }};
        return restTemplate.getForObject(code2sessionUrl, Code2SessionResponse.class, args);
    }
}

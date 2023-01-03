package com.jonas.service;

import com.jonas.repository.mysql.dao.WechatUserDao;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.util.JwtUtil;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final WechatUserDao wechatUserDao;

    public WechatUser saveOrUpdateWechatUser(String openid, String unionid, String sessionKey) throws JOSEException {
        WechatUser wechatUser = wechatUserDao.findById(openid).orElse(null);
        if (wechatUser == null) {
            String token = JwtUtil.generateToken(openid);
            wechatUser = new WechatUser(openid, unionid, sessionKey, token);
            log.info("保存WechatUser成功，{}", wechatUser);
            return wechatUserDao.save(wechatUser);
        } else {
            String oldSessionKey = wechatUser.getSessionKey();
            String oldToken = wechatUser.getToken();
            if (!oldSessionKey.equals(sessionKey)) {
                String token = JwtUtil.generateToken(openid);
                wechatUser.setToken(token);
                wechatUser.setSessionKey(sessionKey);
                log.info("更新WechatUser成功，旧sessionKey为{}，新sessionKey为{}, 旧token为{}，新token为{}",
                        oldSessionKey, sessionKey, oldToken, token);
                return wechatUserDao.save(wechatUser);
            }
        }
        return wechatUser;
    }
}

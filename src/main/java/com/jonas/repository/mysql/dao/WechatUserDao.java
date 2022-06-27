package com.jonas.repository.mysql.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.repository.mysql.mapper.WechatUserMapper;
import com.jonas.util.JwtUtil;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信小程序用户信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-26
 */
@Slf4j
@Service
public class WechatUserDao extends ServiceImpl<WechatUserMapper, WechatUser> implements IService<WechatUser> {

    public WechatUser saveOrUpdateWechatUser(String openid, String unionid, String sessionKey) throws JOSEException {
        WechatUser wechatUser = this.getById(openid);
        if (wechatUser == null) {
            String token = JwtUtil.generateToken(openid);
            wechatUser = new WechatUser(openid, unionid, sessionKey, token);
            if (this.save(wechatUser)) {
                log.info("保存WechatUser成功，{}", wechatUser);
                return wechatUser;
            }
        } else {
            String oldSessionKey = wechatUser.getSessionKey();
            String oldToken = wechatUser.getToken();
            if (!oldSessionKey.equals(sessionKey)) {
                String token = JwtUtil.generateToken(openid);
                wechatUser.setToken(token);
                wechatUser.setSessionKey(sessionKey);
                this.updateById(wechatUser);
                log.info("更新WechatUser成功，旧sessionKey为{}，新sessionKey为{}, 旧token为{}，新token为{}",
                        oldSessionKey, sessionKey, oldToken, token);
                return wechatUser;
            }
        }
        return null;
    }
}

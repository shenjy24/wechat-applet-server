package com.jonas.controller;

import com.jonas.config.request.Anonymous;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.service.AuthService;
import com.jonas.service.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/6/24 18:00
 * @description LoginController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 通过code获得token
     *
     * @param code 微信临时凭证
     * @return token, 服务端与微信小程序的凭证
     */
    @Anonymous
    @RequestMapping("/code2session")
    public String code2session(String code) {
        WechatUser wechatUser = authService.code2session(code);
        return wechatUser.getToken();
    }

    /**
     * 解码用户信息
     *
     * @param rawData       不包括敏感信息的原始数据字符串，用于计算签名
     * @param signature     微信签名，用于校验用户信息
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @return 用户详细信息
     */
    @RequestMapping("/decryptUserProfile")
    public UserProfile decryptUserProfile(String rawData, String signature, String encryptedData, String iv) {
        return authService.decryptUserProfile(rawData, signature, encryptedData, iv);
    }

    @RequestMapping("/updateUserProfile")
    public void updateUserProfile(String avatarUrl, String nickname) {
        authService.updateUserProfile(avatarUrl, nickname);
    }
}

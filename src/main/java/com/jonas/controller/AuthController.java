package com.jonas.controller;

import com.jonas.config.request.Anonymous;
import com.jonas.config.request.User;
import com.jonas.repository.mysql.entity.WechatAccessToken;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.repository.mysql.entity.WechatUserInfo;
import com.jonas.service.UserService;
import com.jonas.service.WechatService;
import com.jonas.service.dto.Code2SessionResponse;
import com.jonas.service.dto.UserProfile;
import com.jonas.service.dto.UserView;
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

    private final WechatService wechatService;
    private final UserService userService;

    /**
     * 通过code获得token
     *
     * @param code 微信临时凭证
     * @return token, 服务端与微信小程序的凭证
     */
    @Anonymous
    @RequestMapping("/code2session")
    public String code2session(String code) {
        Code2SessionResponse response = wechatService.code2session(code);
        WechatUser wechatUser = userService.saveOrUpdateWechatUser(response.getOpenid(), response.getUnionid(), response.getSession_key());
        return wechatUser.getToken();
    }

    /**
     * 获取access_token
     *
     * @return token, 微信服务端的凭证
     */
    @Anonymous
    @RequestMapping("/getAccessToken")
    public WechatAccessToken getAccessToken() {
        return wechatService.getWechatAccessToken();
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
    public UserProfile decryptUserProfile(@User WechatUser user, String rawData, String signature, String encryptedData, String iv) {
        return wechatService.decryptUserProfile(user, rawData, signature, encryptedData, iv);
    }

    @RequestMapping("/updateUserProfile")
    public UserView updateUserProfile(@User WechatUser user, String avatar, String nickname) {
        WechatUserInfo userInfo = userService.updateUserProfile(user, avatar, nickname);
        return userService.buildUserView(userInfo);
    }

    @RequestMapping("/getUserProfile")
    public UserView getUserProfile(@User WechatUser user) {
        WechatUserInfo userInfo = userService.getWechatUserInfo(user.getOpenid());
        return userService.buildUserView(userInfo);
    }
}

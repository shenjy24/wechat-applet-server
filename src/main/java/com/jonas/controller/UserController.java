package com.jonas.controller;

import com.jonas.config.request.User;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.service.WechatService;
import com.jonas.util.GsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author shenjy
 * @time 2024/1/2 18:26
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final WechatService wechatService;

    @PostMapping("/sendMessage")
    public void sendMessage(@User WechatUser user, String templateId, String data) {
        wechatService.sendMessage(user.getOpenid(), templateId, data);
    }
}

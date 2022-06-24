package com.jonas.controller;

import com.jonas.service.AuthService;
import com.jonas.service.dto.Code2SessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/6/24 18:00
 * @description LoginController
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/code2session")
    public Code2SessionResponse code2session(String code) {
        return authService.code2session(code);
    }
}

package com.jonas.config.request;

import com.jonas.repository.mysql.entity.WechatUser;
import lombok.Getter;

/**
 * @author shenjy
 * @createTime 2022/6/27 18:45
 * @description CurrentUser
 */
@Getter
public class WebThreadLocal {
    public static final ThreadLocal<WechatUser> currentUser = new ThreadLocal<>();
}

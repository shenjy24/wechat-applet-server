package com.jonas.config.access;

import lombok.Getter;

/**
 * @author shenjy
 * @date 2020/9/1
 * @description
 */
@Getter
public class CurrentLog {
    public static final ThreadLocal<AccessLog> accessLog = new ThreadLocal<>();
}

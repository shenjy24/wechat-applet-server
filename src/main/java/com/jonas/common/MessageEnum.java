package com.jonas.common;

import lombok.Getter;

/**
 * MessageEnum
 *
 * @author shenjy
 * @time 2024/1/3 09:54
 */
@Getter
public enum MessageEnum {
    MESSAGE1("TSCzvOquGcxGTq5YN0qNSSwpeyx_z3Wb9z3t9WYD9bE", "取餐通知")
    ;

    private final String code;
    private final String message;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

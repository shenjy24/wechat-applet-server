package com.jonas.config.response.model;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description 业务错误码
 */
public enum ErrorCode implements CodeStatus {
    SIGN_ERROR("signature error"),
    EXPRESS_ERROR("express error"),

    BAIDU_ERROR("baidu error"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String message) {
        this.code = SystemCode.BIZ_ERROR.getCode();
        this.message = message;
    }

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

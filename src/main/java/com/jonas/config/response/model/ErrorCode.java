package com.jonas.config.response.model;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description 业务错误码
 */
public enum ErrorCode implements CodeStatus {

    ;

    private final String code;
    private final String message;

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

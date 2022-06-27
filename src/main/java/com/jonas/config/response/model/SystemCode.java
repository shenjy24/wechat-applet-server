package com.jonas.config.response.model;

import com.jonas.config.response.model.CodeStatus;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description 系统状态码
 */
public enum  SystemCode implements CodeStatus {
    SUCCESS("2000", "success"),
    PARAM_ERROR("2001", "parameter error"),
    SERVER_ERROR("2002", "server error"),
    BIZ_ERROR("2004", "business error"),
    ;

    private String code;
    private String message;

    SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

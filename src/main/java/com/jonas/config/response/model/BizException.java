package com.jonas.config.response.model;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description
 */
public class BizException extends RuntimeException {
    private CodeStatus codeStatus;

    private String message;

    public BizException(CodeStatus codeStatus, Throwable cause) {
        super(codeStatus.getMessage());
        this.codeStatus = codeStatus;
        this.message = "BizException:" + codeStatus.getCode() + ":" + codeStatus.getMessage();
    }

    public BizException(CodeStatus codeStatus) {
        super("BizException:" + codeStatus.getCode() + ":" + codeStatus.getMessage());
        this.codeStatus = codeStatus;
        this.message = "BizException:" + codeStatus.getCode() + ":" + codeStatus.getMessage();
    }

    public BizException(String code, String message) {
        super("BizException:" + code + ":" + message);
        this.codeStatus = new CodeStatus() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
        this.message = "BizException:" + code + ":" + message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CodeStatus getCodeStatus() {
        return codeStatus;
    }
}

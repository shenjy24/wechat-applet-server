package com.jonas.repository.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenjy
 * @createTime 2022/6/24 17:55
 * @description Code2SessionResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Code2SessionResponse {
    // 用户唯一标识
    private String openid;
    // 会话密钥
    private String session_key;
    // 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回
    private String unionid;
    // 错误码
    private int errcode;
    // 错误信息
    private String errmsg;
}

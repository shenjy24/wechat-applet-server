package com.jonas.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * getAccessToken接口响应
 *
 * @author shenjy
 * @time 2022/6/24 17:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAccessTokenResponse {
    // 获取到的凭证
    private String access_token;
    // 凭证有效时间，单位：秒。目前是7200秒之内的值。
    private Integer expires_in;
}

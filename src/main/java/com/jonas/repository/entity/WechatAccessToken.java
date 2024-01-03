package com.jonas.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * WechatAccessToken
 *
 * @author shenjy
 * @time 2024/1/2 12:17
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WechatAccessToken {
    /**
     * 微信小程序标识
     */
    @Id
    private String appid;
    /**
     * 微信服务端的通信凭证
     */
    private String accessToken;
    /**
     * 过期时间
     */
    private Timestamp expireTime;
    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;
}

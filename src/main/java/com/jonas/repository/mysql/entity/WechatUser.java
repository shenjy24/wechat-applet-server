package com.jonas.repository.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 微信小程序用户信息表
 * </p>
 *
 * @author shenjy
 * @since 2022-06-26
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WechatUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信小程序用户标识
     */
    @Id
    private String openid;

    /**
     * 微信开放平台帐号用户标识
     */
    private String unionid;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 服务端与小程序通信凭证
     */
    private String token;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public WechatUser(String openid, String unionid, String sessionKey, String token) {
        this.openid = openid;
        this.unionid = unionid;
        this.sessionKey = sessionKey;
        this.token = token;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}

package com.jonas.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 微信小程序应用信息表
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WechatSecret implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信小程序应用标识
     */
    @Id
    private String appid;

    /**
     * 微信小程序应用密钥
     */
    private String secret;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

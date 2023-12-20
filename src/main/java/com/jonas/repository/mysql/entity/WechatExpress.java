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
 * 快递100信息表
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WechatExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 本系统的快递100逻辑ID
     */
    @Id
    private Integer id;

    /**
     * 快递100用户ID
     */
    private String userid;

    /**
     * 快递100授权码
     */
    private String customer;

    /**
     * 快递100授权key
     */
    private String key;

    /**
     * 快递100密码
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

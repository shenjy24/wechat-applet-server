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
 * 百度开放平台信息表
 * </p>
 *
 * @author shenjy
 * @since 2022-06-26
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BaiduApi implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用编码
     */
    @Id
    private Integer appid;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用授权码
     */
    private String ak;

    /**
     * 应用类型
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}

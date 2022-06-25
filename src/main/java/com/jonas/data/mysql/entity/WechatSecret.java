package com.jonas.data.mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@NoArgsConstructor
@AllArgsConstructor
@TableName("wechat_secret")
public class WechatSecret implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信小程序应用标识
     */
    @TableId
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

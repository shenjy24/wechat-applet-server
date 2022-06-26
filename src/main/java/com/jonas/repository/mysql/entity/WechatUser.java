package com.jonas.repository.mysql.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@NoArgsConstructor
@AllArgsConstructor
@TableName("wechat_user")
public class WechatUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信小程序用户标识
     */
    @TableId
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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public WechatUser(String openid, String unionid, String sessionKey) {
        this.openid = openid;
        this.unionid = unionid;
        this.sessionKey = sessionKey;
    }
}

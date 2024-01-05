package com.jonas.repository.bo.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取餐消息
 *
 * @author shenjy
 * @time 2024/1/3 20:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TakeFoodMessage extends WechatMessage {
    private String thing1;
    private String character_string3;
}

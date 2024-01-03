package com.jonas.controller.qo;

import lombok.Data;

import java.util.Map;

/**
 * SendMessageQo
 *
 * @author shenjy
 * @time 2024/1/3 09:41
 */
@Data
public class SendMessageQo {
    private String templateId;
    private Map<String, Object> data;
}

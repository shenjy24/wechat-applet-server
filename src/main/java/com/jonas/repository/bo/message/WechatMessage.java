package com.jonas.repository.bo.message;

import com.jonas.util.GsonUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信消息父类
 *
 * @author shenjy
 * @time 2024/1/3 20:16
 */
@Data
public abstract class WechatMessage {
    /**
     * 获得JSON格式参数
     *
     * @return SON格式参数
     */
    public String toJson() {
        Map<String, Object> data = GsonUtil.toMap(GsonUtil.toJson(this));
        Map<String, Map<String, Object>> dataMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("value", entry.getValue());
            dataMap.put(entry.getKey(), itemMap);
        }
        return GsonUtil.toJson(dataMap);
    }
}

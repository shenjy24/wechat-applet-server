package com.jonas.config.access;

import lombok.Data;

import java.util.Map;

/**
 * @author shenjy
 * @date 2020/9/1
 * @description
 */
@Data
public class AccessLog {
    private long beginTime;
    private String host;
    private String url;
    private String method;
    private Map<String, Object> params;
    private Map<String, Object> headers;
    private long consumeTime; //耗时，毫秒级
    private Object body;
}

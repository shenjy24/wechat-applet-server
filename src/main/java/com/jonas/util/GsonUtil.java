package com.jonas.util;

import com.google.gson.Gson;

/**
 * @author shenjy
 * @date 2020/9/3
 * @description
 */
public class GsonUtil {

    private static final Gson gson = new Gson();

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

    public static <T> T toBean(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }
}

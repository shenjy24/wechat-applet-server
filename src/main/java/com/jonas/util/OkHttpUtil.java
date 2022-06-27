package com.jonas.util;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/02/19
 */
public class OkHttpUtil {

    public static Response synGet(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        Request request = new Request.Builder().url(builder.build()).build();
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    public static <T> T synGet(String url, Class<T> clazz) throws IOException {
        Response response = synGet(url);
        if (response == null) {
            return null;
        }

        String data = response.body().string();
        return GsonUtil.toBean(data, clazz);
    }

    public static Response synGet(String url, Map<String, Object> params) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(builder.build()).build();
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    public static <T> T synGet(String url, Map<String, Object> params, Class<T> clazz) throws IOException {
        Response response = synGet(url, params);
        if (response == null || response.body() == null) {
            return null;
        }

        String data = response.body().string();
        return GsonUtil.toBean(data, clazz);
    }

    public static Response synPost(String url, Map<String, Object> params) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addEncoded(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    public static <T> T synPost(String url, Map<String, Object> params, Class<T> clazz) throws IOException {
        Response response = synPost(url, params);
        if (response == null) {
            return null;
        }

        String data = response.body().string();
        return GsonUtil.toBean(data, clazz);
    }
}

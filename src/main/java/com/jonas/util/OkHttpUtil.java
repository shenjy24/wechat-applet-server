package com.jonas.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/02/19
 */
public class OkHttpUtil {

    private final static OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static Response synGet(String url) throws IOException {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        Request request = new Request.Builder().url(builder.build()).build();
        Call call = client.newCall(request);
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
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(builder.build()).build();
        Call call = client.newCall(request);
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

    /**
     * 表单格式的post请求
     *
     * @param url    路径
     * @param params 参数
     * @return 请求响应
     */
    public static Response syncFormPost(String url, Map<String, String> params) throws IOException {

        FormBody.Builder builder = new FormBody.Builder();
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addEncoded(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        return call.execute();
    }

    /**
     * json格式的post请求
     *
     * @param url    路径
     * @param params 参与
     * @return 响应
     * @throws IOException IO异常
     */
    public static Response syncJsonPost(String url, String params) throws IOException {
        // 创建 RequestBody 包装 JSON 数据
        RequestBody requestBody = RequestBody.create(JSON, params);

        // 构建 POST 请求
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        return client.newCall(request).execute();
    }
}

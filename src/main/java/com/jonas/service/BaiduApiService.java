package com.jonas.service;

import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.ErrorCode;
import com.jonas.config.response.model.SystemCode;
import com.jonas.repository.dao.BaiduApiDao;
import com.jonas.repository.entity.BaiduApi;
import com.jonas.util.GsonUtil;
import com.jonas.util.OkHttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 百度开放平台接口服务
 *
 * @author shenjy
 * @version 1.0
 * @date 2023-01-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaiduApiService {

    @Value("${baidu.api.appid}")
    private int appid;
    @Value("${baidu.api.weather}")
    private String weatherUrl;

    private final BaiduApiDao baiduApiDao;

    public BaiduApi getBaiduApi() {
        Optional<BaiduApi> optional = baiduApiDao.findById(appid);
        if (optional.isEmpty()) {
            throw new BizException(ErrorCode.BAIDU_ERROR);
        }
        return optional.get();
    }

    public Map<String, Object> searchWeather(String districtId) {
        Response response = this.doSearchWeather(districtId);
        if (!response.isSuccessful()) {
            return new HashMap<>();
        }
        try {
            Map<String, Object> body = GsonUtil.toMap(response.body().string());
            if (!body.containsKey("result")) {
                return new HashMap<>();
            }
            String result = GsonUtil.toJson(body.get("result"));
            return GsonUtil.toMap(result);
        } catch (IOException e) {
            log.error("get response body error!", e);
            throw new BizException(ErrorCode.BAIDU_ERROR);
        }
    }

    private Response doSearchWeather(String districtId) {
        try {
            BaiduApi baiduApi = this.getBaiduApi();
            Map<String, Object> params = new HashMap<>() {{
                put("district_id", districtId);
                put("ak", baiduApi.getAk());
                put("data_type", "all");
            }};
            return OkHttpUtil.synGet(weatherUrl, params);
        } catch (IOException e) {
            log.error("search weather error!", e);
            throw new BizException(SystemCode.BIZ_ERROR);
        }
    }
}

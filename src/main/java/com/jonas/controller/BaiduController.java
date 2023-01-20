package com.jonas.controller;

import com.jonas.config.request.Anonymous;
import com.jonas.service.BaiduApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 百度开放平台接口
 *
 * @author shenjy
 * @version 1.0
 * @date 2023-01-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/baidu")
public class BaiduController {

    private final BaiduApiService baiduApiService;

    @Anonymous
    @RequestMapping("/weather/search")
    public Map<String, Object> searchWeather(String districtId) {
        return baiduApiService.searchWeather(districtId);
    }
}

package com.jonas;

import com.jonas.service.BaiduApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private BaiduApiService baiduApiService;

    @Test
    public void testSearchWeather() {
        Map<String, Object> res = baiduApiService.searchWeather("445103");
        System.out.println(res);
    }
}

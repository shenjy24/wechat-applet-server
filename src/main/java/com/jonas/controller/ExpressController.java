package com.jonas.controller;

import com.jonas.config.request.Anonymous;
import com.jonas.service.ExpressService;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.response.QueryTrackResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快递控制类
 *
 * @author shenjy
 * @version 1.0
 * @date 2023-01-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/express")
public class ExpressController {

    private final ExpressService expressService;

    @Anonymous
    @RequestMapping("/queryTrack")
    public QueryTrackResp queryTrack(@RequestBody QueryTrackParam trackParam) {
        return expressService.queryTrack(trackParam);
    }
}

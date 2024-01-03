package com.jonas.service;

import com.google.gson.Gson;
import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.ErrorCode;
import com.jonas.repository.dao.WechatExpressDao;
import com.jonas.repository.entity.WechatExpress;
import com.jonas.util.GsonUtil;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.QueryTrackResp;
import com.kuaidi100.sdk.utils.SignUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 快递接口服务类
 *
 * @author shenjy
 * @version 1.0
 * @date 2023-01-08
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExpressService {

    @Value("${express.id}")
    private Integer expressId;

    private final WechatExpressDao wechatExpressDao;

    /**
     * 实时查询快递信息
     *
     * @param trackParam 快递参数
     * @return
     */
    public QueryTrackResp queryTrack(QueryTrackParam trackParam) {
        WechatExpress express = wechatExpressDao.findById(expressId).orElse(null);
        if (express == null) {
            throw new BizException(ErrorCode.EXPRESS_ERROR);
        }
        return doQueryTrack(express, trackParam);
    }

    private QueryTrackResp doQueryTrack(WechatExpress express, QueryTrackParam trackParam) {
        if (express == null || trackParam == null) {
            return null;
        }
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        String param = new Gson().toJson(trackParam);
        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(express.getCustomer());
        queryTrackReq.setSign(SignUtils.querySign(param, express.getKey(), express.getCustomer()));

        IBaseClient baseClient = new QueryTrack();
        try {
            HttpResult result = baseClient.execute(queryTrackReq);
            if (HttpStatus.SC_OK == result.getStatus()) {
                return GsonUtil.toBean(result.getBody(), QueryTrackResp.class);
            }
        } catch (Exception e) {
            log.error("实时查询快递信息失败!", e);
        }
        return null;
    }
}

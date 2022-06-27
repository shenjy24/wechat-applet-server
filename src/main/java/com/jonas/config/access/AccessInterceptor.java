package com.jonas.config.access;

import com.jonas.common.Constant;
import com.jonas.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @date 2020/9/1
 * @description AccessLog 拦截器
 */
@Slf4j
@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AccessLog accessLog = new AccessLog();
        CurrentLog.accessLog.set(accessLog);

        accessLog.setBeginTime(System.currentTimeMillis());
        accessLog.setHost(request.getRemoteAddr());
        accessLog.setUrl(request.getRequestURI());
        accessLog.setMethod(request.getMethod());
        Map<String, Object> params = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> params.put(key, GsonUtil.toJson(value)));
        accessLog.setParams(params);
        Map<String, Object> headers = new HashMap<>();
        headers.put(Constant.AUTH_HEADER, request.getHeader(Constant.AUTH_HEADER));
        accessLog.setHeaders(headers);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccessLog accessLog = CurrentLog.accessLog.get();
        accessLog.setConsumeTime(System.currentTimeMillis() - accessLog.getBeginTime());
        log.info(accessLog.toString());
    }
}

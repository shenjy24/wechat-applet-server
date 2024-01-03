package com.jonas.config.request;

import com.jonas.common.Constant;
import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.SystemCode;
import com.jonas.repository.dao.WechatUserDao;
import com.jonas.repository.entity.WechatUser;
import com.jonas.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/6/27 17:49
 * @description 请求拦截器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private final WechatUserDao wechatUserDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        // 匿名访问直接返回
        if (hasAnnotation(handlerMethod, Anonymous.class)) {
            return true;
        }

        String token = request.getHeader(Constant.AUTH_HEADER);
        if (StringUtils.isBlank(token)) {
            log.error("Authorization头部不存在");
            throw new BizException(SystemCode.SERVER_ERROR);
        }

        Map<String, Object> payload = JwtUtil.getPayload(token);
        if (!payload.containsKey("openid")) {
            log.error("token负载不存在openid，token为{}", token);
            throw new BizException(SystemCode.SERVER_ERROR);
        }
        String openid = (String) payload.get("openid");
        WechatUser wechatUser = wechatUserDao.findById(openid).orElse(null);
        if (wechatUser == null) {
            log.error("微信用户不存在，openid为{}", openid);
            throw new BizException(SystemCode.SERVER_ERROR);
        }

        request.setAttribute(Constant.REQ_ATT_USER, wechatUser);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean hasAnnotation(HandlerMethod method, Class<? extends Annotation> annotationClass) {
        return method.hasMethodAnnotation(annotationClass) ||
                method.getBeanType().isAnnotationPresent(annotationClass);
    }
}

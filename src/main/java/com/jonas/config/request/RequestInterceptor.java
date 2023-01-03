package com.jonas.config.request;

import com.jonas.common.Constant;
import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.SystemCode;
import com.jonas.repository.mysql.dao.WechatUserDao;
import com.jonas.repository.mysql.entity.WechatUser;
import com.jonas.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
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
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 匿名访问直接返回
        Anonymous anonymous = method.getAnnotation(Anonymous.class);
        if (anonymous != null) {
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
        WebThreadLocal.currentUser.set(wechatUser);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        WebThreadLocal.currentUser.remove();
    }
}

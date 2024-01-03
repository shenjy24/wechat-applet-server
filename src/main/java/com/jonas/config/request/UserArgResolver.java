package com.jonas.config.request;

import com.jonas.common.Constant;
import com.jonas.repository.entity.WechatUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Component
public class UserArgResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(WechatUser.class)
                && parameter.hasParameterAnnotation(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        WechatUser user = (WechatUser) webRequest.getAttribute(Constant.REQ_ATT_USER, RequestAttributes.SCOPE_REQUEST);
        if (user == null) {
            throw new MissingServletRequestPartException(Constant.REQ_ATT_USER);
        }
        return user;
    }
}

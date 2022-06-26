package com.jonas.config.response;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.jonas.config.response.model.BizException;
import com.jonas.config.response.model.JsonResult;
import com.jonas.config.response.model.SystemCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description 全局异常处理器
 */
@Slf4j
@ControllerAdvice
@ConditionalOnMissingClass
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult handle(Exception ex) {
        if (ex instanceof BizException) {
            Iterable iterable = Splitter.on(":").trimResults().omitEmptyStrings().split(ex.getMessage());
            List<String> items = Lists.newArrayList(iterable);
            return new JsonResult(items.get(1), items.get(2), null);
        }
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) ex;
            String message = exception.getMessage().split(",")[0];
            message = message.split(":")[1].trim();
            return new JsonResult(SystemCode.PARAM_ERROR.getCode(), message, null);
        }

        log.error("handle exception", ex);
        return new JsonResult(SystemCode.SERVER_ERROR);
    }
}

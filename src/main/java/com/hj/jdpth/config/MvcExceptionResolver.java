package com.hj.jdpth.config;

import com.hj.jdpth.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MvcExceptionResolver {

    //处理token验证失败异常
    @ExceptionHandler(TokenException.class)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @ResponseBody
    public Map<String, Object> handlerTokenServiceException() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "用户信息已过期，请重新登录");
        return map;
    }
}

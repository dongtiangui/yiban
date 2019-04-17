package com.yiban.yiban_application.common;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({Exception.class,TemplateInputException.class,NullPointerException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Map<String,Object> allException(){
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("meg","系统错误");
        return map;
    }
}

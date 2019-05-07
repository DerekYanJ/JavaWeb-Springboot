package com.example.demo.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    public String getUserId(){
        String token = request.getHeader("token");
        //获取userId并返回
        return token;
    }

}

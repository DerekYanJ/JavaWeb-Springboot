package com.example.demo.springboot.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo.base.ApiResponse;
import com.example.demo.springboot.token.TokenIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class MainInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenIgnore annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethod().getAnnotation(TokenIgnore.class);
        }else{
            return true;
        }

        //如果有@AuthIgnore注解，则不验证token
        if(annotation != null){
            return true;
        }

        //获取用户凭证
        String token = request.getHeader("token");

        //token凭证为空
        if(token == null || token.isEmpty()){
            ApiResponse<Object> apiResponse = new ApiResponse<>(100,"token不能为空",null);
            printContent(response, JSON.toJSONString(apiResponse));
            return false;
        }else{
            String redisToken = (String) redisTemplate.opsForValue().get(token);
            if(redisToken == null || redisToken.isEmpty()){
                ApiResponse<Object> apiResponse = new ApiResponse<>(100,"token失效",null);
                printContent(response, JSON.toJSONString(apiResponse));
                return false;
            }
        }
        return true;
    }

    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

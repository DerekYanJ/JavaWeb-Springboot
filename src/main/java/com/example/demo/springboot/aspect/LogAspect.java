package com.example.demo.springboot.aspect;


import com.alibaba.fastjson.JSON;
import com.example.demo.springboot.annotation.Logs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 在这里定义切面的点，Pointcut的表达式语法需要匹配到你调用的方法中
     */
    @Pointcut("within(com.example.demo.controller..*)")
    public void declareJoinPointExpression() {
    }

    @Before("declareJoinPointExpression()")
    public void addBeforeLogger(JoinPoint joinPoint) {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String userAgent = request.getHeader("User-Agent");
            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().endsWith("Token")) {
                        cookie = c;
                        break;
                    }
                }
            }
            String CookieString=cookie==null?"":JSON.toJSONString(cookie);
            String params = JSON.toJSONString(request.getParameterMap());

            String apiName = "";
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();

            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    Class[] clazzs = m.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        if (m.getDeclaredAnnotation(Logs.class) != null) {
                            apiName = m.getDeclaredAnnotation(Logs.class).desc();
                            break;
                        }
                    }
                }
            }
            if(logger.isDebugEnabled() && !apiName.isEmpty())
                logger.info("调用开始：apiName:{}, url: {} ,uri: {}, method: {}, params: {},Cookie:{},UserAgent:{}",
                    apiName,url,uri, method, params, CookieString,userAgent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Around(value = "declareJoinPointExpression()")
    public Object doAround(ProceedingJoinPoint proceeding) throws Throwable {
//        long beforeTime=System.currentTimeMillis();
        //执行被拦截的方法 result是返回结果
        Object result = proceeding.proceed();
        //debug模式下才计算方法耗时
//        if (logger.isDebugEnabled()) {
//            long afterTime=System.currentTimeMillis();
//            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//            HttpServletRequest request = sra.getRequest();
//            logger.info("请求：{} , 耗时：{}ms",request.getRequestURI(),afterTime-beforeTime);
//        }
        //此处可以在log输出result，依据业务要求处理
        if (logger.isDebugEnabled())
            logger.info("调用结束：返回值:{}",
                JSON.toJSONString(result));

        return result;
    }
}

package com.example.demo.springboot.exception;

import com.example.demo.base.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    private static Logger logger= LoggerFactory.getLogger(CommonExceptionHandler.class);
    /**
     *  拦截Exception类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e){
        //日志记录捕获的异常信息
        logger.error("异常信息：" + e.getMessage());
        logger.error(getTrace(e));
        if(e instanceof CustomException){
            return new ApiResponse<>(((CustomException) e).getCode(),e.getMessage(), null);
        }
        return new ApiResponse<>(500, "系统开小差了，请稍等！", null);
    }

    public String getTrace(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        return trace.toString();
    }

}

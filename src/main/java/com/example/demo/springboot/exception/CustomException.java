package com.example.demo.springboot.exception;

import com.example.demo.base.ApiResponse;

/**
 * 自定义异常 包含错误码
 */
public class CustomException extends Exception{
    private int code;

    public int getCode() {
        return code;
    }

    public CustomException(int code, String msg){
        super(msg);
        this.code = code;
    }
}

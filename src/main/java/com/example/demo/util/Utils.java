package com.example.demo.util;

import com.example.demo.base.ApiResponse;

public class Utils {
    public static ApiResponse success(){
        return new ApiResponse<>(0, "请求成功", null);
    }
    public static ApiResponse error(){
        return new ApiResponse<>(500, "请求异常", null);
    }
}

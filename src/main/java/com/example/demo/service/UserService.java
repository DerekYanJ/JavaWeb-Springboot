package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.springboot.exception.CustomException;

public interface UserService {
    User login(String username, String pwd);
    void logout(String token) throws CustomException;
}

package com.example.demo.service.impl;

import com.example.demo.base.ApiResponse;
import com.example.demo.base.BaseServiceImpl;
import com.example.demo.bean.User;
import com.example.demo.bean.dao.UserDao;
import com.example.demo.service.UserService;
import com.example.demo.springboot.exception.CustomException;
import com.example.demo.springboot.redis.RedisService;
import com.example.demo.springboot.redis.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

//    @Autowired
    private RedisServiceImpl redisServiceImpl;

    @Autowired
    public UserServiceImpl(RedisServiceImpl service){
        this.redisServiceImpl = service;
    }

    @Autowired
    UserDao userDao;

    @Override
    public User login(String username, String pwd) {

        System.out.println("登录成功");

        User user = new User();
        user.setName(username);
        user.setPassword(pwd);

        redisServiceImpl.set(username, username); //添加缓存
        logger.info("添加token：" + redisServiceImpl.get(username));

        return user;
    }

    @Override
    public void logout(String token) throws CustomException {
        if("yan1".equals(token)){
            logger.info("退出成功");
        }else{
            logger.error("抛异常了");
            throw new CustomException(300, "退出失败 撒啊啊");
        }
    }
}

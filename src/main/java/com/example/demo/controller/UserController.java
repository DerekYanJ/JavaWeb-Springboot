package com.example.demo.controller;

import com.example.demo.base.ApiResponse;
import com.example.demo.base.BaseController;
import com.example.demo.bean.User;
import com.example.demo.bean.dao.UserDao;
import com.example.demo.service.UserService;
import com.example.demo.springboot.annotation.Logs;
import com.example.demo.springboot.exception.CustomException;
import com.example.demo.springboot.redis.RedisServiceImpl;
import com.example.demo.springboot.token.TokenIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseController {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    @RequestMapping("/testException")
    @ResponseBody
    public ApiResponse testException() {
        String nullStr = "我是数字";
        System.out.println(Integer.parseInt(nullStr) + "");
        return new ApiResponse<Object>(500,"错误发生了");
    }

    @RequestMapping("/login")
    @ResponseBody
    @TokenIgnore
    public ApiResponse login(HttpServletRequest request){
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        if(username == null || username.isEmpty()){
            return  new ApiResponse<>(500,"请输入帐号");
        }else if (pwd == null || pwd.isEmpty()){
            return  new ApiResponse<>(500,"请输入密码");
        }

        User user = userService.login(username,pwd);
        return new ApiResponse<>("登录成功", user);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ApiResponse logout(HttpServletRequest request) throws Exception{
        String token = request.getHeader("token");
        String token1 = getUserId();
        System.out.println("进来了？？？:" + token1);
        userService.logout(token);
        return new ApiResponse<>("退出成功", null);
    }

    @RequestMapping("/login1")
    public String login1(@RequestParam String username,
                         @RequestParam String pwd,
                         HttpServletRequest request){
        String code = request.getParameter("code");

        if(username == null || username.isEmpty()){
            return "请输入帐号";
        }else if (pwd == null || pwd.isEmpty()){
            return "请输入密码";
        }

        return "登录成功：" + username + " code:" + code;
    }

    @RequestMapping(path = {"/helloSpringBoot"})
    public String HelloSpring (){
        System.out.println("hello spring boot");
        return "{ content: \"老板 我爱你三千遍！！！\" }";
    }
/*
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","world");
        return "home";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable(value = "id") int id){
//        User u = userDao.findById(id);
//        model.addAttribute("name",u.getName());
//        return u.getName();
        return "{}";
    }*/
}
package com.shiyanlou.controller;

import com.shiyanlou.entity.UserEntity;
import com.shiyanlou.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    private UserMapper userMapper;

    // 默认显示首页
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 注册页面展示
    @GetMapping("/register")
    public String register() {
        // Spring 会自动转换为对应的 register.jsp
        return "register";
    }

    // 用于 AJAX 的 POST 请求
    @PostMapping(path = "/do_register", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String doRegister(@RequestParam String username, @RequestParam String password1, @RequestParam String password2) {
        if (! password1.equals(password2)) {
            return "两次密码不同";
        }
        UserEntity userEntity = userMapper.findUserByUsername(username);
        if (userEntity != null) {
            return "此用户名已经被注册";
        }

        userMapper.addUser(username, password1, System.currentTimeMillis());
        return "注册成功";
    }

    // 展示登入页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 用于 AJAX 的 POST 请求
    @PostMapping(path = "/do_login", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String doLogin(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        UserEntity userEntity = userMapper.findUserByUsernameAndPassword(username, password);
        if (userEntity == null) {
            return "用户名或密码错误";
        }

        // 添加 session
        request.getSession().setAttribute("username", username);
        return "登入成功";
    }

    // 处理退出操作，移除 session
    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        // 跳转到首页
        return "redirect:/";
    }
}
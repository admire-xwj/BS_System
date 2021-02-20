package com.bs.bs_system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录Controller
 */
@Controller
@RequestMapping("/")
@Slf4j
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        //登录
        try {
            subject.login(token);

        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "/login";
        }
        model.addAttribute("msg", "登录成功");
        return "redirect:/index?userName=" + userName;

    }

    @RequestMapping("/logout")
    public String logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";

    }

}

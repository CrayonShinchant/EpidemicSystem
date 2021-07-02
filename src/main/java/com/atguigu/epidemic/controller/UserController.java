package com.atguigu.epidemic.controller;


import com.atguigu.epidemic.beans.UserInfo;
import com.atguigu.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制层
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping ("/login")
    private String login(UserInfo userInfo, Model model) {
        UserInfo user = userService.findByAccount(userInfo.getAccount());
        if (user == null) {
            //账号不正确
            model.addAttribute("msg", "账号不正确");
            return "login";
        }
        //登录成功
        if (user.getPassword().equals(userInfo.getPassword())) {
            return "redirect:/epidemic.jsp";
        }
        model.addAttribute("msg", "账号不正确");
        return "login";
    }
}

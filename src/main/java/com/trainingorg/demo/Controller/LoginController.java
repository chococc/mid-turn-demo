package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.LoginService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value="/login_service")
    public HttpRequest user_login(@RequestParam("username") String username,@RequestParam("password") String password){
        System.out.println(username);
        System.out.println(password);
        return loginService.login(username,password);
    }
    @RequestMapping("/checkLoginStatus")
    public HttpRequest check_login() {
        return loginService.check_login();
    }
    @RequestMapping("/logout")
    public HttpRequest user_logout() {
        return loginService.logout();
    }
    @RequestMapping("/iForgot")
    public HttpRequest user_reset_password(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false)String password){return loginService.reset_password(username,password);}
}
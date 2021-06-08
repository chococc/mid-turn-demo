package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.LoginService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.*;



@RestController
public class LoginController {

    LoginService loginService=new LoginService();

    @RequestMapping("/login")
    public HttpRequest user_login(@RequestParam(value="username",required = false) String username, @RequestParam(value="password",required = false) String password){
        return loginService.user_login_service(username,password);
    }
    @RequestMapping("/checkLoginStatus")
    public HttpRequest check_login() {
        return loginService.check_login_service();
    }
    @RequestMapping("/logout")
    public HttpRequest user_logout() {
        return loginService.logout_service();
    }
    @RequestMapping("/iforgot")
    public HttpRequest user_reset_password(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false)String password){return loginService.user_reset_password(username,password);}
}
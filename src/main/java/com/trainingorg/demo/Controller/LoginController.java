package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.LoginService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@CrossOrigin
public class LoginController {

    @Resource
    LoginService loginService;

    @RequestMapping(value="/login_service")
    public HttpRequest user_login(@RequestBody Map<String,Object> map){
        System.out.println(map.get("username"));
        System.out.println(map.get("password"));
        return loginService.login(map.get("username").toString(),map.get("password").toString());
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
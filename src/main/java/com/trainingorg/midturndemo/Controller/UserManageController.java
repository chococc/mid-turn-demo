package com.trainingorg.midturndemo.Controller;

import com.trainingorg.midturndemo.Service.UserService;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")

public class UserManageController {

    protected UserService userService=new UserService();

    @RequestMapping("/adduser")
    public HttpRequest adduser(@RequestParam(value="username",required = false) String username,@RequestParam(value="password",required = false) String password){
        return userService.addUser_service(username,password);
    }

    @RequestMapping("/deleteUser")
    public HttpRequest deleteuser(@RequestParam(value="username",required = false) String username){
        return userService.deleteUser_service(username);
    }

    @RequestMapping("/deleteuserByToken")
    public HttpRequest deleteuserByToken(){
        return userService.deleteUserByToken_service();
    }

    @RequestMapping("/edituser4admin")
    public HttpRequest edituser_admin(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "identity",required = false) String identity,@RequestParam(value = "phone",required = false) String phone,@RequestParam(value = "Org",required = false) String Org){
        return userService.editUsers_service(username,name,identity,phone,Org);
    }

    @RequestMapping("edituser4Customer")
    public HttpRequest edituser_Customer(@RequestParam(value = "name",required = false)String name,@RequestParam(value = "identity",required = false) String identity,@RequestParam(value = "phone",required = false) String phone,@RequestParam(value = "Org",required = false) String Org){
        return userService.editUsers_Customer_service(name,identity,phone,Org);
    }
}

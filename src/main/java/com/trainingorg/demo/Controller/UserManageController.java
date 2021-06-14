package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.UserService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")

public class UserManageController {

    protected UserService userService=new UserService();

    @RequestMapping("/add")
    public HttpRequest adduser(@RequestBody Map<String,Object> map){
        return userService.addUserService(map.get("username").toString(),map.get("password").toString());
    }

    @RequestMapping("/delete")
    public HttpRequest deleteuser(@RequestParam(value="username",required = false) String username){
        return userService.deleteUserService(username);
    }

    @RequestMapping("/deleteByToken")
    public HttpRequest deleteuserByToken(){
        return userService.deleteUserByTokenService();
    }

    @RequestMapping("/edituser4admin")
    public HttpRequest edituser_admin(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "identity",required = false) String identity,@RequestParam(value = "phone",required = false) String phone,@RequestParam(value = "Org",required = false) String Org){
        return userService.editUsers_service(username,name,identity,phone,Org);
    }

    @RequestMapping("edituser4Customer")
    public HttpRequest edituser_Customer(@RequestParam(value = "name",required = false)String name,@RequestParam(value = "identity",required = false) String identity,@RequestParam(value = "phone",required = false) String phone,@RequestParam(value = "Org",required = false) String Org){
        return userService.editUsersCustomerService(name,identity,phone,Org);
    }

    @RequestMapping(value="selectAll")
    public HttpRequest user_selectAll(){
        return userService.selectUserSelectAll();
    }

    @RequestMapping(value = "selectByID")
    public HttpRequest user_selectByID(@RequestParam(value = "username",required = false) String username){
        return userService.selectUserSelectByIDService(username);
    }
}
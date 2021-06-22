package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.UserService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")

public class UserManageController {

    protected UserService userService;

    @RequestMapping("/add")
    public HttpRequest add(@RequestParam("username")String username,@RequestParam("password")String password){
        return userService.add(username,password);
    }

    @RequestMapping("/delete")
    public HttpRequest delete(@RequestParam(value="username",required = false) String username){
        return userService.delete(username);
    }

    @RequestMapping("/deleteByToken")
    public HttpRequest deleteByToken(){
        return userService.deleteUserByToken();
    }

    @RequestMapping("/edit4admin")
    public HttpRequest edit_admin(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "name",required = false)String name,@RequestParam(value = "identity",required = false) String identity,@RequestParam(value = "phone",required = false) String phone,@RequestParam(value = "Org",required = false) String Org){
        return userService.editUsers(username,name,identity,phone,Org);
    }

    @RequestMapping("edit4Customer")
    public HttpRequest edit_Customer(@RequestParam(value = "name",required = false)String name,@RequestParam(value = "phone",required = false) String phone,@RequestParam(value = "Org",required = false) String Org){
        return userService.editUsersCustomer(name,phone,Org);
    }

    @RequestMapping(value="selectAll")
    public HttpRequest selectAll(){
        return userService.selectAll();
    }

    @RequestMapping(value = "selectByID")
    public HttpRequest selectByID(@RequestParam(value = "username",required = false) String username){
        return userService.selectByID(username);
    }
}
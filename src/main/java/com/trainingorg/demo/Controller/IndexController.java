package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.Impl.SysMenuServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController

public class IndexController{
    @Resource
    protected SysMenuServiceImpl sysMenuServiceImpl;

    @GetMapping("")
    public String login(){return "login-1";}
    @GetMapping("/menu")
    public Map<String,Object> menu(){
        return sysMenuServiceImpl.menu();
    }
}

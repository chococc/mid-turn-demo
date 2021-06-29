package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.Impl.SysMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller

public class IndexController{
    @Resource
    protected SysMenuServiceImpl sysMenuServiceImpl;

    @GetMapping("/login")
    public String login(){return "login-1";}
    @GetMapping("/menu")
    public Map<String,Object> menu(){
        return sysMenuServiceImpl.menu();
    }
    @GetMapping("/index")
    public String index(){return "../index";}
}

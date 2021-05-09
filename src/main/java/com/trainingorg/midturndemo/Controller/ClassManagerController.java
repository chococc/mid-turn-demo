package com.trainingorg.midturndemo.Controller;

import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/class")
public class ClassManagerController {
    @RequestMapping(value = "/addcourse")
    public HttpRequest addcourse(@RequestParam(value = "coursename",required = false) String coursename){
        HttpRequest httpRequest=new HttpRequest();
        return httpRequest;
    }
}

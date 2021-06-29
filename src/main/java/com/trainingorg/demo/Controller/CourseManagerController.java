package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.CourseManagerService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/course")

public class CourseManagerController {

    @Resource
    protected CourseManagerService courseManagerService;

    protected HttpRequest httpRequest=new HttpRequest();

    @RequestMapping(value = "/add")
    public HttpRequest addCourse(@RequestParam(value = "coursename",required = false) String coursename){
        httpRequest= courseManagerService.add(coursename);
        return httpRequest;
    }

    @RequestMapping(value = "/delete")
    public HttpRequest deleteCourse(@RequestParam(value="coursename",required = false) String coursename){
        httpRequest= courseManagerService.delete(coursename);
        return httpRequest;
    }

    @RequestMapping(value = "/edit")
    public HttpRequest editCourse(@RequestParam(value = "coursename",required = false) String courseName,@RequestParam(value = "cost") float Cost,@RequestParam(value = "status") int status){
        httpRequest= courseManagerService.edit(courseName,Cost,status);
        return httpRequest;
    }

    @RequestMapping(value="/selectAll")
    public HttpRequest selectAll(){
        httpRequest=courseManagerService.selectAll();
        return httpRequest;
    }

    @RequestMapping(value = "/selectByID")
    public HttpRequest selectByID(@RequestParam(value = "coursename",required = false) String coursename){
        httpRequest=courseManagerService.selectByID(coursename);
        return httpRequest;
    }
}

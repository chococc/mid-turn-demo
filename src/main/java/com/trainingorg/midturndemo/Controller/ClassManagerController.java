package com.trainingorg.midturndemo.Controller;

import com.trainingorg.midturndemo.Service.ClassManagerService;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/class")

public class ClassManagerController {

    protected ClassManagerService classManagerService=new ClassManagerService();
    protected HttpRequest httpRequest=new HttpRequest();

    @RequestMapping(value = "/addcourse")
    public HttpRequest addCourse(@RequestParam(value = "coursename",required = false) String coursename){
        httpRequest= classManagerService.addCourseService(coursename);
        return httpRequest;
    }

    @RequestMapping(value = "/deletecourse")
    public HttpRequest deleteCourse(@RequestParam(value="coursename",required = false) String coursename){
        httpRequest=classManagerService.deleteCourseService(coursename);
        return httpRequest;
    }

    @RequestMapping(value = "/editCourse")
    public HttpRequest editCourse(@RequestParam(value = "coursename",required = false) String coursename,@RequestParam(value = "cost",required = false) String Cost,@RequestParam(value = "status",required = false) String status){
        httpRequest=classManagerService.editCourseService(coursename,Cost,status);
        return httpRequest;
    }
}

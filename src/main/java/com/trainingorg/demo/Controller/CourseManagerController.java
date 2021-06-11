package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.CourseManagerService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/course")

public class CourseManagerController {

    protected CourseManagerService courseManagerService =new CourseManagerService();
    protected HttpRequest httpRequest=new HttpRequest();

    @RequestMapping(value = "/add")
    public HttpRequest addCourse(@RequestParam(value = "coursename",required = false) String coursename){
        httpRequest= courseManagerService.addCourseService(coursename);
        return httpRequest;
    }

    @RequestMapping(value = "/delete")
    public HttpRequest deleteCourse(@RequestParam(value="coursename",required = false) String coursename){
        httpRequest= courseManagerService.deleteCourseService(coursename);
        return httpRequest;
    }

    @RequestMapping(value = "/edit")
    public HttpRequest editCourse(@RequestParam(value = "coursename",required = false) String courseName,@RequestParam(value = "cost") float Cost,@RequestParam(value = "status") int status){
        httpRequest= courseManagerService.editCourseService(courseName,Cost,status);
        return httpRequest;
    }

    @RequestMapping(value="/selectAll")
    public HttpRequest selectAll(){
        httpRequest=courseManagerService.selectAllService();
        return httpRequest;
    }

    @RequestMapping(value = "/selectByID")
    public HttpRequest selectByID(@RequestParam(value = "coursename",required = false) String coursename){
        httpRequest=courseManagerService.selectByID(coursename);
        return httpRequest;
    }
}

package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.ClassManagerService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/class")
public class ClassManagerController {

    ClassManagerService classManagerService=new ClassManagerService();
    @RequestMapping(value = "/add")
    public HttpRequest addClassController(@RequestParam(value = "teacherID",required = false) String teacherID,@RequestParam(value = "courseID",required = false) String courseID,@RequestParam(value = "startTime",required = false) int startWeek,@RequestParam(value = "stopTime",required = false) int stopWeek){
        return classManagerService.add(teacherID,courseID,startWeek,stopWeek);
    }

    @RequestMapping(value = "/delete")
    public HttpRequest deleteClassController(@RequestParam(value = "classID",required = false) String classID){
        return classManagerService.delete(classID);
    }

    @RequestMapping(value = "/update")
    public HttpRequest updateClassController(@RequestParam(value = "teacherID",required = false) String teacherID,@RequestParam(value = "courseID",required = false) String courseID,@RequestParam(value = "startTime",required = false) int startTime,@RequestParam(value = "stopTime",required = false) int stopTime){
        return classManagerService.update(teacherID,courseID,startTime,stopTime);
    }

    @RequestMapping(value = "/selectAll")
    public HttpRequest selectAll(){
        return  classManagerService.selectAll();
    }
}

package com.trainingorg.midturndemo.Controller;

import com.trainingorg.midturndemo.Service.ClassManagerService;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/class")
public class ClassManagerController {

    ClassManagerService classManagerService=new ClassManagerService();
    @RequestMapping(value = "/addclass")
    public HttpRequest addClassController(@RequestParam(value = "teacherID",required = false) String teacherID,@RequestParam(value = "courseID",required = false) String courseID,@RequestParam(value = "startTime",required = false) String startTime,@RequestParam(value = "stopTime",required = false) String stopTime){
        return classManagerService.addClassManagerService(teacherID,courseID,startTime,stopTime);
    }
}

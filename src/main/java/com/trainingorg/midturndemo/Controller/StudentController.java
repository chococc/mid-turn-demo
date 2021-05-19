package com.trainingorg.midturndemo.Controller;

import com.trainingorg.midturndemo.Service.StudentService;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stu")
public class StudentController {

    StudentService studentService =new StudentService();

    @RequestMapping(value = "/sc")
    public HttpRequest ChoseClass(@RequestParam(value = "classID",required = false) String classID){
        return studentService.choseClass(classID);
    }

    @RequestMapping(value="/schedule")
    public HttpRequest schedule(){
        return studentService.getTimeTable();
    }
}

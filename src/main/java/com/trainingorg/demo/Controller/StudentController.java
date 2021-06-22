package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.StudentService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stu")

public class StudentController {

    StudentService studentService;

    @RequestMapping(value = "/sc")
    public HttpRequest ChoseClass(@RequestParam(value = "classID") String classID){
        return studentService.choseClass(classID);
    }

    @RequestMapping(value="/schedule")
    public HttpRequest schedule(@RequestParam(value = "instance") String instance){
        return studentService.getTimeTable(instance);
    }

    @RequestMapping(value="/selectAll")
    public HttpRequest selectAll(){
        return studentService.selectAll();
    }

    @RequestMapping(value = "/getPayList")
    public HttpRequest getPayList(){
        return studentService.getCost();
    }

    @RequestMapping(value = "/pay")
    public HttpRequest pay(){
        return studentService.getPay();
    }

    @RequestMapping("/grade")
    public HttpRequest grade(@RequestParam(value = "classID",required = false)int classID,@RequestParam(value = "grade",required = false)int grade){return studentService.score(classID,grade);}

    @RequestMapping(value = "/delete")
    public HttpRequest delete(@RequestParam(value = "classID",required = false)String classID){
        return studentService.delete(classID);
    }
}

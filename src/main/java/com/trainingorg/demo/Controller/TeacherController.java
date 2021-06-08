package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.TeacherService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/teacher")
public class TeacherController {

    TeacherService teacherService = new TeacherService();

    @RequestMapping(value = "/getClass")
    public HttpRequest getClass(@RequestParam(value = "instance") String instance) {
        return teacherService.getClassList(instance);
    }

    @RequestMapping(value = "/getAllClass")
    public HttpRequest getAllClass() {
        return teacherService.getAllClassList();
    }

    @RequestMapping(value = "/getStudentList")
    public HttpRequest getStudentList(@RequestParam(value = "classID", required = false) String classID) {
        return teacherService.getStudentList(classID);
    }

    @RequestMapping(value = "/setGrade")
    public HttpRequest setGrade(@RequestParam(value = "classID",required = false) String classID,@RequestParam(value = "studentID",required = false) String studentID,@RequestParam(value = "grade",required = false) int grade){
        return teacherService.setGrade(classID,studentID,grade);
    }

}

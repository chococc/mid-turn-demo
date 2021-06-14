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
    public HttpRequest add(@RequestParam(value = "teacherID") String teacherID,@RequestParam(value = "courseID") String courseID,@RequestParam(value = "startTime") int startWeek,@RequestParam(value = "stopTime") int stopWeek){
        return classManagerService.add(teacherID,courseID,startWeek,stopWeek);
    }

    @RequestMapping(value = "/delete")
    public HttpRequest delete(@RequestParam(value = "classID") String classID){
        return classManagerService.delete(classID);
    }

    @RequestMapping(value = "/update")
    public HttpRequest update(@RequestParam(value = "teacherID") String teacherID,@RequestParam(value = "courseID") String courseID,@RequestParam(value = "startTime") int startTime,@RequestParam(value = "stopTime",required = false) int stopTime){
        return classManagerService.update(teacherID,courseID,startTime,stopTime);
    }

    @RequestMapping(value="/checkHere")
    public HttpRequest check(@RequestParam(value="studentId")String studentId,@RequestParam(value = "classId")int classId){return classManagerService.checkHere(studentId,classId);}

    @RequestMapping(value = "/flash")
    public HttpRequest flash(){return classManagerService.flashState();}
    @RequestMapping(value = "/selectAll")
    public HttpRequest selectAll(){
        return  classManagerService.selectAll();
    }
}

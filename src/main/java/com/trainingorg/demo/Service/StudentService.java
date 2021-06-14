package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.bean.Entity.ClassEntity;
import com.trainingorg.demo.bean.Entity.CourseEntity;
import com.trainingorg.demo.bean.Entity.StudentClassEntity;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.ClassManagerDao;
import com.trainingorg.demo.dao.CourseManageDao;
import com.trainingorg.demo.dao.StudentDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class StudentService {

    HttpRequest httpRequest=new HttpRequest();
    StudentDao studentDao=new StudentDao();

    public HttpRequest choseClass(String classID){
        try {
            if(studentDao.selectByID(classID)!=null){
                httpRequest.setCode(502);
                httpRequest.setRequestMessage("已选择过该课程，请到课程表确认，若课程表中不包含此课程，请先支付该课程的学费。");
            }else {
                studentDao.choseClass(classID);
                httpRequest.setCode(200);
                httpRequest.setRequestMessage("选课成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("选课失败，请联系管理员");
            httpRequest.setCode(501);
        }
        return httpRequest;
    }

    public HttpRequest getTimeTable(String instance){

        List<StudentClassEntity> classList;
        List<ClassEntity> todayClass=new ArrayList<>();
        try {
            classList = studentDao.selectClassListByStudent();
            if(classList!=null){
                for (StudentClassEntity studentClassEntity : classList) {
                    ClassEntity flag=studentDao.selectClassByInstance(studentClassEntity,instance);
                    //if(flag.stillOn())
                    todayClass.add(flag);
                }
            }
            httpRequest.setData(JSON.toJSON(todayClass));
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setCode(200);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(502);
            httpRequest.setRequestMessage("查询失败");
        }
        //System.out.println(todayClass);
        return httpRequest;
    }

    public HttpRequest getCost(){

        List<StudentClassEntity> notPayList;
        ClassEntity classEntity;
        CourseEntity course;
        int total=0;
        try{
            notPayList= studentDao.notPayList();
            for(StudentClassEntity studentClassEntity:notPayList){
                classEntity= new ClassManagerDao().selectByID(studentClassEntity.getClassId());
                course=new CourseManageDao().selectByID(classEntity.getCourseId());
                total+=course.getCost();
            }
            httpRequest.setRequestMessage("共计需支付 "+total+" 元");
            httpRequest.setCode(200);
            httpRequest.setData(notPayList);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(504);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest deleteClass(String classID){
        try{
            if(studentDao.getClassStatus(classID)==1){
                httpRequest.setCode(506);
                httpRequest.setRequestMessage("已支付该课程学费，本系统不支持本操作.");
            }else {
                studentDao.deleteClass(classID);
                httpRequest.setRequestMessage("删除成功");
                httpRequest.setCode(200);
            }
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(505);
            httpRequest.setRequestMessage(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest getPay(){

        try {
            studentDao.payClass();
            httpRequest.setRequestMessage("支付成功");
            httpRequest.setCode(200);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("支付失败");
            httpRequest.setCode(507);
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try {
            httpRequest.setCode(200);
            httpRequest.setData(JSON.toJSON(studentDao.selectAll()));
            httpRequest.setRequestMessage("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(503);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }
}

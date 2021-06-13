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
            studentDao.choseClass(classID);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("选课成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("学生不存在");
            httpRequest.setRequestCode(501);
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
                    todayClass.add(studentDao.selectClassByInstance(studentClassEntity,instance));
                }
            }
            httpRequest.setRequestData(JSON.toJSON(todayClass));
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestCode(200);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(502);
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
            httpRequest.setRequestCode(200);
            httpRequest.setRequestData(notPayList);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(504);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest deleteClass(String classID){
        try{
            studentDao.deleteClass(classID);
            httpRequest.setRequestMessage("删除成功");
            httpRequest.setRequestCode(200);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(505);
            httpRequest.setRequestMessage("删除失败");
        }
        return httpRequest;
    }

    public HttpRequest getPay(){

        try {
            studentDao.payClass();
            httpRequest.setRequestMessage("支付成功");
            httpRequest.setRequestCode(200);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("支付失败");
            httpRequest.setRequestCode(506);
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try {
            httpRequest.setRequestCode(200);
            httpRequest.setRequestData(JSON.toJSON(studentDao.selectAll()));
            httpRequest.setRequestMessage("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(503);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }
}

package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.Util.MysqlActuator;
import org.springframework.stereotype.Service;

@Service

public class ClassManagerService {

    protected MysqlActuator mysqlActuator=new MysqlActuator();
    protected HttpRequest httpRequest=new HttpRequest();

    public HttpRequest addCourseService(String coursename){
        try {
            mysqlActuator.update("INSERT INTO CourseList(courseName) Values('" + coursename + "')");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程添加成功"+coursename);
        }catch (Exception e){
            httpRequest.setRequestCode(301);
            httpRequest.setRequestMessage("课程管理服务存在问题@addcourse:"+e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest deleteCourseService(String coursename){
        try {
            mysqlActuator.update("DELETE FROM CourseList where courseName='" + coursename + "'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程删除成功："+coursename);
        }catch (Exception e){
            httpRequest.setRequestCode(301);
            httpRequest.setRequestMessage("课程管理服务存在问题@deletecourse:"+e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editCourseService(String coursename,String cost,String state){

        return httpRequest;
    }
}

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
            e.printStackTrace();
            httpRequest.setRequestCode(301);
            httpRequest.setRequestMessage("课程已存在"+e.getMessage());
        }

        return httpRequest;
    }

    public HttpRequest deleteCourseService(String coursename){
        try {
            mysqlActuator.update("DELETE FROM CourseList where courseName='" + coursename + "'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程删除成功："+coursename);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(302);
            httpRequest.setRequestMessage("课程管理服务存在问题@deletecourse:"+e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editCourseService(String coursename,String cost,String state){
        try{
            mysqlActuator.update("UPDATE CourseList SET coursename='"+coursename+"',courseCost="+cost+",courseState="+state+" where coursename='"+coursename+"'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程修改成功："+coursename);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestData(303);
            httpRequest.setRequestMessage("课程管理服务存在问题@editCourseService:"+e.getMessage());
        }
        return httpRequest;
    }
}
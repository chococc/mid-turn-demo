package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.bean.Entity.CourseEntity;
import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.Util.MysqlActuator;
import org.springframework.stereotype.Service;

@Service

public class CourseManagerService {

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

    public HttpRequest selectAllService(){
        try {
            httpRequest.setRequestData(new MysqlActuator().getForList(CourseEntity.class, "SELECT * from CourseList"));
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (Exception e){
            httpRequest.setRequestCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }

    public HttpRequest selectByID(String courseName){
        try{
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("数据拉取成功");
            httpRequest.setRequestData(mysqlActuator.getForList(CourseEntity.class,"SELECT * FROM CourseList where courseName LIKE '"+courseName+"'"));
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(306);
            httpRequest.setRequestMessage("课程表拉取失败@selectCourseByID:"+e.getMessage());
        }
        return httpRequest;
    }
}
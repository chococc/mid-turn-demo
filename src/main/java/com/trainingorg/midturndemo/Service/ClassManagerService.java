package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.bean.Entity.ClassEntity;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.stereotype.Service;

@Service
public class ClassManagerService {

    MysqlActuator mysqlActuator=new MysqlActuator();
    HttpRequest httpRequest=new HttpRequest();

    public HttpRequest addClassManagerService(String teacherId,String courseID,String startTime, String stopTime){
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startTime,stopTime);
        try{
            mysqlActuator.update("INSERT INTO classList(teacherId,courseId,startTime,endTime,courseName,teacherName) Values('"+classEntity.getTeacherId()+"','"+classEntity.getCourseId()+"','"+classEntity.getStartTime()+"','"+classEntity.getStopTime()+"','"+classEntity.getCourseName()+"','"+classEntity.getTeacherName()+"')");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("班级创建成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(401);
            httpRequest.setRequestMessage("信息表单存在错误.");
        }
        return httpRequest;
    }
}

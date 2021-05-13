package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.Util.TimeStamp;
import com.trainingorg.midturndemo.bean.Entity.ClassEntity;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ClassManagerService {

    MysqlActuator mysqlActuator=new MysqlActuator();
    HttpRequest httpRequest=new HttpRequest();
    TimeStamp timeStamp=new TimeStamp();

    public HttpRequest addClassManagerService(String teacherId,String courseID,String startTime, String stopTime){
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startTime,stopTime);
        try{
            mysqlActuator.update("INSERT INTO ClassList(teacherid,courseId,starttime,endtime) Values('"+classEntity.getTeacherId()+"','"+classEntity.getCourseId()+"','"+classEntity.getStartTime()+"','"+classEntity.getStopTime()+"')");
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

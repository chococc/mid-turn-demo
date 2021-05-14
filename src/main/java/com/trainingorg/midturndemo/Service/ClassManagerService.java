package com.trainingorg.midturndemo.Service;

import com.alibaba.fastjson.JSON;
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

    public HttpRequest deleteClassService(String classID){
        try{
            mysqlActuator.update("DELETE FROM classList where classID="+classID);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("班级信息删除成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(402);
            httpRequest.setRequestMessage("课程信息删除失败");
        }
        return httpRequest;
    }

    public HttpRequest updateClassService(String teacherId,String courseID,String startTime, String stopTime){
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startTime,stopTime);
        try{
            mysqlActuator.update("UPDATE classList SET teacherId='"+classEntity.getTeacherName()+"',courseId='"+classEntity.getCourseId()+"',startTime='"+classEntity.getStartTime()+"',stopTime='"+classEntity.getStopTime()+"',courseName='"+classEntity.getCourseName()+"',teacherName='"+classEntity.getTeacherName()+"'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("班级信息修改成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(403);
            httpRequest.setRequestMessage("课程信息修改失败");
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try{
            httpRequest.setRequestData(JSON.toJSON(mysqlActuator.getForList(ClassEntity.class,"SELECT * from classList")));
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("数据拉取成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(404);
            httpRequest.setRequestMessage("数据拉取失败");
        }
        return httpRequest;
    }
}

package com.trainingorg.midturndemo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.dao.ClassManagerDao;
import org.springframework.stereotype.Service;

@Service
public class ClassManagerService {

    HttpRequest httpRequest=new HttpRequest();
    ClassManagerDao classManagerDao=new ClassManagerDao();

    public HttpRequest addClassManagerService(String teacherId,String courseID,int startWeek, int stopWeek){
        try{
            classManagerDao.AddClass(teacherId,courseID,startWeek,stopWeek);
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
            classManagerDao.deleteClass(classID);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("班级信息删除成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(402);
            httpRequest.setRequestMessage("课程信息删除失败");
        }
        return httpRequest;
    }

    public HttpRequest updateClassService(String teacherId,String courseID,int startTime, int stopTime){
        try{
            classManagerDao.updateClass(teacherId,courseID,startTime,stopTime);
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
            httpRequest.setRequestData(JSON.toJSON(classManagerDao.selectAll()));
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

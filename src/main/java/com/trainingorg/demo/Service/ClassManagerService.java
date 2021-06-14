package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.ClassManagerDao;
import org.springframework.stereotype.Service;

@Service
public class ClassManagerService {

    HttpRequest httpRequest=new HttpRequest();
    ClassManagerDao classManagerDao=new ClassManagerDao();

    public HttpRequest addClassManagerService(String teacherId,String courseID,int startWeek, int stopWeek){
        try{
            classManagerDao.AddClass(teacherId,courseID,startWeek,stopWeek);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级创建成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(401);
            httpRequest.setRequestMessage("信息表单存在错误.");
        }
        return httpRequest;
    }

    public HttpRequest deleteClassService(String classID){
        try{
            classManagerDao.deleteClass(classID);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级信息删除成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(402);
            httpRequest.setRequestMessage("课程信息删除失败");
        }
        return httpRequest;
    }

    public HttpRequest updateClassService(String teacherId,String courseID,int startTime, int stopTime){
        try{
            classManagerDao.updateClass(teacherId,courseID,startTime,stopTime);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("班级信息修改成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(403);
            httpRequest.setRequestMessage("课程信息修改失败");
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try{
            httpRequest.setData(JSON.toJSON(classManagerDao.selectAll()));
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("数据拉取成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(404);
            httpRequest.setRequestMessage("数据拉取失败");
        }
        return httpRequest;
    }
}

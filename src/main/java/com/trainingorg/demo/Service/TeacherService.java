package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.TeacherDao;
import org.springframework.stereotype.Service;

@Service

public class TeacherService {

    protected HttpRequest httpRequest=new HttpRequest();
    protected TeacherDao teacherDao=new TeacherDao();

    public HttpRequest getClassList(String instance){

        try{
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestData(JSON.toJSON(teacherDao.getClassListByInstance(instance)));
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(601);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest getAllClassList(){
        try{
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestData(JSON.toJSON(teacherDao.getClassListByTeacherID()));
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(601);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest getStudentList(String classID){
        try{
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestData(JSON.toJSON(teacherDao.getStudentList(classID)));
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(602);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest setGrade(int classID,String studentID,int grade){
        try {
            teacherDao.setGrade(classID,studentID,grade);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("成绩录入成功");
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(603);
            httpRequest.setRequestMessage("成绩录入失败");
        }
        return httpRequest;
    }
}

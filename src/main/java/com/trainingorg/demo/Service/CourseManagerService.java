package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.CourseManageDao;
import org.springframework.stereotype.Service;

@Service

public class CourseManagerService {

    protected CourseManageDao courseManageDao=new CourseManageDao();
    protected HttpRequest httpRequest=new HttpRequest();

    public HttpRequest addCourseService(String courseName){
        try {
            courseManageDao.addCourse(courseName);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程添加成功"+courseName);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(301);
            httpRequest.setRequestMessage("课程已存在"+e.getMessage());
        }

        return httpRequest;
    }

    public HttpRequest deleteCourseService(String courseName){
        try {
            courseManageDao.deleteCourse(courseName);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程删除成功："+courseName);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(302);
            httpRequest.setRequestMessage("课程管理服务存在问题@deletecourse:"+e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editCourseService(String courseName,float cost,int state){
        try{
            courseManageDao.editCourse(courseName,cost,state);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("课程修改成功："+courseName);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestData(303);
            httpRequest.setRequestMessage("课程管理服务存在问题@editCourseService:"+e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest selectAllService(){
        try {
            httpRequest.setRequestData(courseManageDao.selectAll());
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
            httpRequest.setRequestData(courseManageDao.selectByID(courseName));
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(306);
            httpRequest.setRequestMessage("课程表拉取失败@selectCourseByID:"+e.getMessage());
        }
        return httpRequest;
    }
}
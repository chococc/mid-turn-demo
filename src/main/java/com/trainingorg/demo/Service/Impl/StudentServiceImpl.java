package com.trainingorg.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.Service.StudentService;
import com.trainingorg.demo.Util.NoToken;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.ClassEntity;
import com.trainingorg.demo.bean.Entity.CourseEntity;
import com.trainingorg.demo.bean.Entity.StudentClassEntity;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.ClassManagerDao;
import com.trainingorg.demo.dao.CourseManageDao;
import com.trainingorg.demo.dao.StudentDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao=new StudentDao();

    public HttpRequest choseClass(String classID){
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("student");
            if(studentDao.selectByID(classID)!=null){
                httpRequest.setCode(502);
                httpRequest.setRequestMessage("已选择过该课程，请到课程表确认，若课程表中不包含此课程，请先支付该课程的学费。");
            }else {
                studentDao.choseClass(classID);
                httpRequest.setCode(200);
                httpRequest.setRequestMessage("选课成功");
            }
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }
        catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("选课失败，课程不存在.");
            httpRequest.setCode(501);
        }
        return httpRequest;
    }

    public HttpRequest getTimeTable(String instance){
        HttpRequest httpRequest=new HttpRequest();
        List<StudentClassEntity> classList;
        List<ClassEntity> todayClass=new ArrayList<>();
        try {
            new Token().IdentityCheck("student");
            classList = studentDao.selectClassListByStudent();
            System.out.println(classList);
            if(classList!=null){
                for (StudentClassEntity studentClassEntity : classList) {
                    ClassEntity flag=studentDao.selectClassByInstance(studentClassEntity,instance);
                    if(flag!=null&&flag.getStatus()==1)
                    todayClass.add(flag);
                }
            }
            httpRequest.setData(JSON.toJSON(todayClass));
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setCode(200);
        } catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(503);
            httpRequest.setRequestMessage("存在时间设置错误的课程,请联系管理员");
        }
        return httpRequest;
    }

    public HttpRequest getCost(){
        HttpRequest httpRequest=new HttpRequest();
        List<StudentClassEntity> notPayList;
        ClassEntity classEntity;
        CourseEntity course;
        float total=0;
        try{
            new Token().IdentityCheck("student");
            notPayList= studentDao.notPayList();
            for(StudentClassEntity studentClassEntity:notPayList){
                classEntity= new ClassManagerDao().selectByID(studentClassEntity.getClassId());
                System.out.println(studentClassEntity.getClassId());
                course=new CourseManageDao().selectByName(classEntity.getCourseName());
                System.out.println(classEntity.getCourseName());
                total+=course.getCost();
            }
            httpRequest.setRequestMessage("共计需支付 "+total+" 元");
            httpRequest.setCode(200);
            httpRequest.setData(notPayList);
        } catch (NoToken n){
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(504);
            httpRequest.setRequestMessage("存在尚未标价的课程,请联系管理员.");
        }
        return httpRequest;
    }

    public HttpRequest delete(String classID){
        HttpRequest httpRequest=new HttpRequest();
        try{
            new Token().IdentityCheck("student");
            if(studentDao.getClassStatus(classID)==1){
                httpRequest.setCode(506);
                httpRequest.setRequestMessage("已支付该课程学费，本系统不支持本操作.");
            }else {
                studentDao.deleteClass(classID);
                httpRequest.setRequestMessage("删除成功");
                httpRequest.setCode(200);
            }
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }
        catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(505);
            httpRequest.setRequestMessage(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest getPay(){
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("student");
            studentDao.payClass();
            httpRequest.setRequestMessage("支付成功");
            httpRequest.setCode(200);
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }
        catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("支付失败");
            httpRequest.setCode(507);
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("student");
            httpRequest.setCode(200);
            httpRequest.setData(JSON.toJSON(studentDao.selectAll()));
            httpRequest.setRequestMessage("查询成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }
        catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(508);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest score(int classID,int score){
        HttpRequest httpRequest=new HttpRequest();
        try{
            new Token().IdentityCheck("student");
            if(studentDao.selectByID(String.valueOf(classID))==null){
                httpRequest.setCode(510);
                httpRequest.setRequestMessage("你未选择该课程，无法评分.");
            }else {
                studentDao.setGrade(classID, score);
                httpRequest.setCode(200);
                httpRequest.setRequestMessage("已成功更新评分.");
            }
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非学生账号.");
            return httpRequest;
        }
        catch (Exception e){
            httpRequest.setCode(509);
            httpRequest.setRequestMessage("无法更新评分，请联系管理员.");
        }
        return httpRequest;
    }
}

package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.Util.NoToken;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.TeacherDao;
import org.springframework.stereotype.Service;

@Service

public class TeacherService {

    protected HttpRequest httpRequest=new HttpRequest();
    protected TeacherDao teacherDao=new TeacherDao();

    public HttpRequest getClassList(String instance){
        try{
            new Token().IdentityCheck("teacher");
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setData(JSON.toJSON(teacherDao.getClassListByInstance(instance)));
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非教师账号.");
            return httpRequest;
        } catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(601);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest getAllClassList(){
        try{
            new Token().IdentityCheck("teacher");
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setData(JSON.toJSON(teacherDao.getClassListByTeacherID()));
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非教师账号.");
            return httpRequest;
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(601);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest getStudentList(String classID){
        try{
            new Token().IdentityCheck("teacher");
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setData(JSON.toJSON(teacherDao.getStudentList(classID)));
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非教师账号.");
            return httpRequest;
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setCode(602);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest setGrade(int classID,String studentID,int grade){
        try {
            new Token().IdentityCheck("teacher");
            teacherDao.setGrade(classID,studentID,grade);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("成绩录入成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非教师账号.");
            return httpRequest;
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setCode(603);
            httpRequest.setRequestMessage("成绩录入失败");
        }
        return httpRequest;
    }
}

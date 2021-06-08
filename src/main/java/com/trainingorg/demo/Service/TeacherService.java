package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.TimeStamp;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.ClassEntity;
import com.trainingorg.demo.bean.Entity.StudentClassEntity;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.stereotype.Service;

@Service

public class TeacherService {

    protected HttpRequest httpRequest=new HttpRequest();
    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public HttpRequest getClassList(String instance){
        Token token=new Token();
        String username=token.Token2Username();
        if(instance==null){
            instance=new TimeStamp().getInstance();
        }
        try{
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestData(JSON.toJSON(mysqlActuator.getForList(ClassEntity.class,"SELECT * FROM classList where teacherID='"+username+"' AND (ClassTime1 like '+"+instance+"%'or ClassTime2 like '"+instance+"%' or ClassTime3 like '"+instance+"%')")));
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(601);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest getAllClassList(){
        Token token=new Token();
        String username=token.Token2Username();
        try{
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestData(JSON.toJSON(mysqlActuator.getForList(ClassEntity.class,"SELECT * FROM classList where teacherID='"+username+"'")));
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
            httpRequest.setRequestData(JSON.toJSON(mysqlActuator.getForList(StudentClassEntity.class,"SELECT * FROM studentClass where classID='+"+classID+"'")));
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(602);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public HttpRequest setGrade(String classID,String studentID,int grade){
        try {
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("成绩录入成功");
            System.out.println("UPDATE studentClass SET grade="+grade+" where classID='"+classID+"' and studentID='"+studentID+"'");
            mysqlActuator.update("UPDATE studentClass SET grade="+grade+" where classID='"+classID+"' and studentID='"+studentID+"'");
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(603);
            httpRequest.setRequestMessage("成绩录入失败");
        }
        return httpRequest;
    }
}

package com.trainingorg.midturndemo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.Util.TimeStamp;
import com.trainingorg.midturndemo.Util.Token;
import com.trainingorg.midturndemo.bean.Entity.ClassEntity;
import com.trainingorg.midturndemo.bean.Entity.StudentClassEntity;
import com.trainingorg.midturndemo.bean.Entity.UserEntity;
import com.trainingorg.midturndemo.bean.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class StudentService {

    HttpRequest httpRequest=new HttpRequest();
    MysqlActuator mysqlActuator=new MysqlActuator();

    public HttpRequest choseClass(String classID){
        Token token=new Token();
        String username=token.Token2Username();
        try {
            UserEntity userEntity = mysqlActuator.get(UserEntity.class, "SELECT * from Users where username = '"+username+"'");
            mysqlActuator.update("INSERT into studentClass(studentId,studentName,classId) values('"+username+"','"+userEntity.getName()+"','"+classID+"')");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("选课成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("学生不存在");
            httpRequest.setRequestCode(501);
        }
        return httpRequest;
    }

    public HttpRequest getTimeTable(String instance){
        Token token=new Token();
        String username=token.Token2Username();
        if(instance==null) {
            instance = new TimeStamp().getInstance();
        }
        List<StudentClassEntity> classList;
        List<ClassEntity> todayClass=new ArrayList<>();
        try {
            classList = mysqlActuator.getForList(StudentClassEntity.class, "SELECT * from studentClass where studentId='" + username + "'");
            if(classList!=null){
                for (StudentClassEntity studentClassEntity : classList) {
                    todayClass.add(mysqlActuator.get(ClassEntity.class,"SELECT * from classList where classId='"+studentClassEntity.getClassId()+"'AND (ClassTime1 like '+"+instance+"%'or ClassTime2 like '"+instance+"%' or ClassTime3 like '"+instance+"%')"));
                }
            }
            httpRequest.setRequestData(JSON.toJSON(todayClass));
            httpRequest.setRequestMessage("查询成功");
            httpRequest.setRequestCode(200);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(502);
            httpRequest.setRequestMessage("查询失败");
        }
        //System.out.println(todayClass);
        return httpRequest;
    }
}

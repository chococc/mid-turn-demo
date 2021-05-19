package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.Util.MysqlActuator;
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
            mysqlActuator.update("INSERT into studentclass(studentId,studentName,classId) values('"+username+"','"+userEntity.getName()+"','"+classID+"')");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("选课成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestMessage("学生不存在");
            httpRequest.setRequestCode(501);
        }
        return httpRequest;
    }

    public HttpRequest getTimeTable(){
        Token token=new Token();
        String username=token.Token2Username();
        List<StudentClassEntity> classList;
        List<ClassEntity> todayClass=new ArrayList<>();
        try {
            System.out.println("SELECT classID from studentclass where studentId='" + username + "'");
            classList = mysqlActuator.getForList(StudentClassEntity.class, "SELECT * from studentclass where studentId='" + username + "'");
            if(classList!=null){
                for (StudentClassEntity studentClassEntity : classList) {
                    todayClass.add(mysqlActuator.get(ClassEntity.class,"SELECT * from classList where classid='"+studentClassEntity.getClassid()));
                }
            }
        }catch (Exception e){e.printStackTrace();}
        System.out.println(todayClass);
        return httpRequest;
    }
}

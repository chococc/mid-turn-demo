package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.SQLUtils;
import com.trainingorg.demo.Util.TimeStamp;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.ClassEntity;
import com.trainingorg.demo.bean.Entity.StudentClassEntity;
import com.trainingorg.demo.bean.Entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao {

    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public void choseClass(String classID) throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        UserEntity userEntity=new LoginUserDao().selectByID(username);
        //mysqlActuator.update("INSERT into studentClass(studentId,studentName,classId) values('"+username+"','"+userEntity.getName()+"','"+classID+"')");
        Map<String,Object> map=new HashMap<>();
        map.put("studentId",username);
        map.put("studentName",userEntity.getName());
        map.put("classId",classID);
        mysqlActuator.update(SQLUtils.getSql("studentClass","insert",map,false,""));
    }

    public List<StudentClassEntity> selectClassListByStudent() throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        System.out.println("SELECT * from studentClass where studentId='" + username + "'and status=1");
        return mysqlActuator.getForList(StudentClassEntity.class, "SELECT * from studentClass where studentId='" + username + "'and status=0");
    }

    public ClassEntity selectClassByInstance(StudentClassEntity studentClassEntity,String instance) throws Exception{
        if(instance==null) {
            instance = new TimeStamp().getInstance();
        }
        System.out.println("SELECT * from classList where classId='"+studentClassEntity.getClassId()+"'AND (ClassTime1 like '+"+instance+"%'or ClassTime2 like '"+instance+"%' or ClassTime3 like '"+instance+"%')");
        return mysqlActuator.get(ClassEntity.class,"SELECT * from classList where classId='"+studentClassEntity.getClassId()+"'AND (ClassTime1 like '+"+instance+"%'or ClassTime2 like '"+instance+"%' or ClassTime3 like '"+instance+"%')");
    }

    public void deleteClass(String classID) throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        mysqlActuator.update("DELETE from studentClass where studentID='"+username+"' and classID='"+classID+"'");
    }

    public int getClassStatus(String classID) throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        return mysqlActuator.get(StudentClassEntity.class,"SELECT * from studentCalss where studentID='"+username+"' and classID='"+classID+"'").getStatus();
    }

    public List<StudentClassEntity> notPayList() throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        return mysqlActuator.getForList(StudentClassEntity.class,"SELECT * from studentClass where studentId='"+username+"' and status=0");
    }

    public void payClass() throws Exception{
        Token token=new Token();
        String username= token.Token2Username();
        //mysqlActuator.update("UPDATE studentClass SET pay=1 where studentID='"+username+"'");
        Map<String,Object> map=new HashMap<>();
        map.put("pay",1);
        map.put("Key_studentID",username);
        mysqlActuator.update(SQLUtils.getSql("studentClass","update",map,false,""));
    }

    public void setGrade(int classID,String studentID,int teacherGrade) throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("teacherGrade",teacherGrade);
        map.put("Key_studentID",studentID);
        map.put("Key_classID",classID);
        mysqlActuator.update(SQLUtils.getSql("StudentClass","update",map,false,null));
    }

    public StudentClassEntity selectByID(String classID) throws Exception {
        Token token=new Token();
        String username=token.Token2Username();
        System.out.println("SELECT * from studentClass where studentId='" + username + "' and classID="+classID);
        return mysqlActuator.get(StudentClassEntity.class,"SELECT * from studentClass where studentId='" + username + "' and classID="+classID);
    }

    public List<StudentClassEntity> selectAll() throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        return mysqlActuator.getForList(StudentClassEntity.class,"SELECT * from studentClass where studentId='" + username + "'");
    }
}
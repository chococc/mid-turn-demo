package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.*;
import com.trainingorg.demo.bean.Entity.ClassEntity;
import com.trainingorg.demo.bean.Entity.StudentClassEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassManagerDao {

    MysqlActuator mysqlActuator = new MysqlActuator();

    public void AddClass(String teacherId,String courseID,int startWeek,int stopWeek) throws Exception {
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startWeek,stopWeek);
        //mysqlActuator.update("INSERT INTO classList(teacherId,courseId,startWeek,endWeek,courseName,teacherName) Values('"+classEntity.getTeacherId()+"','"+classEntity.getCourseId()+"','"+classEntity.getStartWeek()+"','"+classEntity.getEndWeek()+"','"+classEntity.getCourseName()+"','"+classEntity.getTeacherName()+"')");
        Map<String,Object> map=new HashMap<>();
        map.put("teacherId",classEntity.getTeacherId());
        map.put("courseID",classEntity.getCourseId());
        map.put("startWeek",classEntity.getStartWeek());
        map.put("endWeek",classEntity.getEndWeek());
        map.put("courseName",classEntity.getCourseName());
        map.put("teacherName",classEntity.getTeacherName());
        mysqlActuator.update(SQLUtils.getSql("classList","insert",map,false,""));
    }

    public void deleteClass(String classID) throws SQLException {
        mysqlActuator.update("DELETE FROM classList where classID="+classID);
    }

    public void updateClass(String teacherId,String courseID,int startTime,int stopTime) throws Exception {
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startTime,stopTime);
        //mysqlActuator.update("UPDATE classList SET teacherId='"+classEntity.getTeacherName()+"',courseId='"+classEntity.getCourseId()+"',startTime='"+classEntity.getStartWeek()+"',stopTime='"+classEntity.getEndWeek()+"',courseName='"+classEntity.getCourseName()+"',teacherName='"+classEntity.getTeacherName()+"'");
        Map<String,Object> map=new HashMap<>();
        map.put("teacherId",classEntity.getTeacherId());
        map.put("courseID",classEntity.getCourseId());
        map.put("startWeek",classEntity.getStartWeek());
        map.put("endWeek",classEntity.getEndWeek());
        map.put("courseName",classEntity.getCourseName());
        map.put("teacherName",classEntity.getTeacherName());
        mysqlActuator.update(SQLUtils.getSql("classList","update",map,false,""));
    }

    public List<ClassEntity> selectAll() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.getForList(ClassEntity.class,"SELECT * from classList");
    }

    public ClassEntity selectByID(String classID) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.get(ClassEntity.class,"SELECT * from classList where classID="+classID);
    }

    public void checkHere(String studentID,int classID) throws Exception {
        Token token=new Token();
        String username=token.Token2Username();
        ClassEntity flag=mysqlActuator.get(ClassEntity.class,"SELECT * fro classList where classID="+classID+"and teacherId='"+username+"'");
        if(flag==null)
            throw new NoToken("课程与任课教师不对应。");
        Map<String,Object>map=new HashMap<>();
        map.put("studentId",studentID);
        map.put("classId",classID);
        map.put("dateTime", new TimeStamp().getNowTimestamp().toString());
        mysqlActuator.update(SQLUtils.getSql("checkHere","insert",map,false,null));
    }

    public void setTime(int classID,String time1,String time2,String time3) throws Exception {
        Token token=new Token();
        String username=token.Token2Username();
        //System.out.println("SELECT * fro classList where classID="+classID+"and teacherId='"+username+"'");
        ClassEntity flag=mysqlActuator.get(ClassEntity.class,"SELECT * from classList where classID="+classID+" and teacherId='"+username+"'");
        if(flag==null)
            throw new NoToken("课程与任课教师不对应。");
        Map<String,Object>map=new HashMap<>();
        map.put("classTime1",time1);
        map.put("classTime2",time2);
        map.put("classTime3",time3);
        map.put("Key_classId",classID);
        //System.out.println(SQLUtils.getSql("classList","update",map,false,null));
        mysqlActuator.update(SQLUtils.getSql("classList","update",map,false,null));
    }

    public void flashStatus() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<ClassEntity> allClass=selectAll();
        for(ClassEntity classEntity:allClass){
            List<StudentClassEntity> studentClassEntities0= mysqlActuator.getForList(StudentClassEntity.class,"SELECT * from studentClass where classId ="+classEntity.getClassId()+"and status=0");
            List<Long> count=mysqlActuator.getForValue("SELECT count(id) from studentClass");
            if(count.get(0)!=0&&studentClassEntities0.size()==count.get(0)){
                mysqlActuator.update("UPDATE classList SET values(status=0) where classId="+classEntity.getClassId());
            }else{
                mysqlActuator.update("UPDATE classList SET values(status=1) where classId="+classEntity.getClassId());
            }
        }
    }
}
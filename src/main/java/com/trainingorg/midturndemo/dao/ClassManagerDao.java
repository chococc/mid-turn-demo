package com.trainingorg.midturndemo.dao;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.bean.Entity.ClassEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ClassManagerDao {

    MysqlActuator mysqlActuator = new MysqlActuator();

    public void AddClass(String teacherId,String courseID,int startWeek,int stopWeek) throws SQLException {
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startWeek,stopWeek);
        mysqlActuator.update("INSERT INTO classList(teacherId,courseId,startWeek,endWeek,courseName,teacherName) Values('"+classEntity.getTeacherId()+"','"+classEntity.getCourseId()+"','"+classEntity.getStartWeek()+"','"+classEntity.getEndWeek()+"','"+classEntity.getCourseName()+"','"+classEntity.getTeacherName()+"')");
    }

    public void deleteClass(String classID) throws SQLException {
        mysqlActuator.update("DELETE FROM classList where classID="+classID);
    }

    public void updateClass(String teacherId,String courseID,int startTime,int stopTime) throws SQLException {
        ClassEntity classEntity=new ClassEntity(teacherId,courseID,startTime,stopTime);
        mysqlActuator.update("UPDATE classList SET teacherId='"+classEntity.getTeacherName()+"',courseId='"+classEntity.getCourseId()+"',startTime='"+classEntity.getStartWeek()+"',stopTime='"+classEntity.getEndWeek()+"',courseName='"+classEntity.getCourseName()+"',teacherName='"+classEntity.getTeacherName()+"'");
    }

    public List<ClassEntity> selectAll() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.getForList(ClassEntity.class,"SELECT * from classList");
    }

    public List<ClassEntity> selectByID(String classID) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.getForList(ClassEntity.class,"SELECT * from classList where classID="+classID);
    }
}

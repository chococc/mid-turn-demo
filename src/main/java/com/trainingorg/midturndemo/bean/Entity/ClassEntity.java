package com.trainingorg.midturndemo.bean.Entity;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.Util.TimeStamp;
import lombok.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Data

public class ClassEntity {

    MysqlActuator mysqlActuator=new MysqlActuator();
    TimeStamp timeStamp=new TimeStamp();
    protected int classId;
    protected String teacherId;
    protected String courseId;
    protected String teacherName;
    protected String courseName;
    protected int startWeek;
    protected int endWeek;

    public ClassEntity(){}

    public ClassEntity(String teacherId,String courseId,int startTime,int stopTime) {
        this.teacherId=teacherId;
        this.courseId=courseId;
        try {
            System.out.println("SELECT name from Users where username=" + teacherId + " AND identify='teacher'");
            teacherName = mysqlActuator.get(UserEntity.class,"SELECT * from Users where username=" + teacherId + " AND identify='teacher'").getName();

            System.out.println("SELECT courseName from CourseList where courseID="+courseId);
            courseName = mysqlActuator.get(CourseEntity.class,"SELECT * from CourseList where courseID="+courseId).getCourseName();

        }catch (Exception e){
            e.printStackTrace();
            teacherName=null;
            courseName=null;
        }
        this.startWeek=startTime;
        this.endWeek =stopTime;
    }

    public boolean stillOn(int week,String classid) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ClassEntity classEntity=new MysqlActuator().get(ClassEntity.class,"SELECT * FROM classList where classid='"+classid+"'");
        return week>classEntity.startWeek&&week<classEntity.endWeek;
    }
}

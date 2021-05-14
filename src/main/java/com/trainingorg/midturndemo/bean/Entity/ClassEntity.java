package com.trainingorg.midturndemo.bean.Entity;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.Util.TimeStamp;
import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class ClassEntity {

    MysqlActuator mysqlActuator=new MysqlActuator();
    TimeStamp timeStamp=new TimeStamp();
    protected int classId;
    protected String teacherId;
    protected String courseId;
    protected String teacherName;
    protected String courseName;
    protected Timestamp startTime;
    protected Timestamp stopTime;

    public ClassEntity(){}

    public ClassEntity(String teacherId,String courseId,String startTime,String stopTime) {
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
        this.startTime=timeStamp.getTimestamp(startTime);
        this.stopTime=timeStamp.getTimestamp(stopTime);
    }
}

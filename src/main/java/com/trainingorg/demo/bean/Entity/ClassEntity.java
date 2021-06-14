package com.trainingorg.demo.bean.Entity;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.TimeStamp;
import lombok.*;

@Data

public class ClassEntity {

    TimeStamp timeStamp=new TimeStamp();
    MysqlActuator mysqlActuator=new MysqlActuator();

    protected int classId;
    protected String teacherId;
    protected String courseId;
    protected String teacherName;
    protected String courseName;
    protected int startWeek;
    protected int endWeek;
    protected int status;

    public ClassEntity(){}

    public ClassEntity(String teacherId,String courseId,int startWeek,int stopWeek) throws Exception {
        this.teacherId=teacherId;
        this.courseId=courseId;
        teacherName = mysqlActuator.get(UserEntity.class,"SELECT * from Users where username='" + teacherId + "' AND identify='teacher'").getName();
        courseName = mysqlActuator.get(CourseEntity.class,"SELECT * from CourseList where courseID="+courseId).getCourseName();
        this.startWeek=startWeek;
        this.endWeek =stopWeek;
    }
}
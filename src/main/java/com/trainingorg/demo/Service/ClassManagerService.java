package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.stereotype.Service;

@Service
public interface ClassManagerService {
    HttpRequest add(String teacherID,String courseID,int startWeek,int stopWeek);
    HttpRequest delete(String classID);
    HttpRequest update(String teacherId,String courseID,int startWeek,int stopWeek);
    HttpRequest setTime(int classId,String time1,String time2,String time3);
    HttpRequest flashState();
    HttpRequest checkHere(String studentID,int classID);
    HttpRequest selectAll();
}

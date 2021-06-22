package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface TeacherService {
    HttpRequest getClassList(String instance);
    HttpRequest getAllClassList();
    HttpRequest getStudentList(String classID);
    HttpRequest setGrade(int classID,String studentID,int grade);

}

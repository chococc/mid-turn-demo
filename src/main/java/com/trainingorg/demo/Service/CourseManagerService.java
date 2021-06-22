package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface CourseManagerService {
    HttpRequest add(String courseName);
    HttpRequest delete(String courseName);
    HttpRequest edit(String courseName,float cost,int state);
    HttpRequest selectByID(String courseName);
    HttpRequest selectAll();
}

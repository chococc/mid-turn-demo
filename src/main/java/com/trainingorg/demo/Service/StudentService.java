package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface StudentService {
    HttpRequest choseClass(String classID);
    HttpRequest getTimeTable(String instance);
    HttpRequest getCost();
    HttpRequest delete(String classID);
    HttpRequest getPay();
    HttpRequest selectAll();
    HttpRequest score(int classID,int score);
}

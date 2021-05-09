package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.bean.MysqlActuator;
import org.springframework.stereotype.Service;

@Service
public class ClassManagerService {
    MysqlActuator mysqlActuator=new MysqlActuator();
    public HttpRequest addcourseService(String coursename){
        mysqlActuator.update("INSERT INTO CourseList Values("+coursename+",");
    }
}

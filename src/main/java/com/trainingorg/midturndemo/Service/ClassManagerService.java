package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.Util.MysqlActuator;
import org.springframework.stereotype.Service;

@Service
public class ClassManagerService {
    MysqlActuator mysqlActuator=new MysqlActuator();
    public HttpRequest addcourseService(String coursename){
        HttpRequest httpRequest=new HttpRequest();
        mysqlActuator.update("INSERT INTO CourseList Values("+coursename+",");
        return httpRequest;
    }
}

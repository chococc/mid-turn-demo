package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.SQLUtils;
import com.trainingorg.demo.bean.Entity.CourseEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManageDao {

    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public void addCourse(String courseName) throws Exception {
        //mysqlActuator.update("INSERT INTO CourseList(courseName) Values('" + courseName + "')");
        Map<String,String> map=new HashMap<>();
        map.put("courseName",courseName);
        mysqlActuator.update(SQLUtils.getSql("CourseList","update",map,false,""));
    }

    public void deleteCourse(String courseName) throws Exception {
        //mysqlActuator.update("DELETE FROM CourseList where courseName='" + courseName + "'");
        Map<String,String> map=new HashMap<>();
        map.put("courseName",courseName);
        mysqlActuator.update(SQLUtils.getSql("CourseList","delete",map,false,""));
    }

    public void editCourse(String courseName,float cost,int state) throws Exception {
        //mysqlActuator.update("UPDATE CourseList SET courseName='"+courseName+"',courseCost="+cost+",courseState="+state+" where courseName='"+courseName+"'");
        Map <String,Object>map=new HashMap<>();
        map.put("courseName",courseName);
        map.put("cost",cost);
        map.put("state",state);
        map.put("Key_courseName",courseName);
        //System.out.println(SQLUtils.getSql("CourseList","update",map,false,""));
        mysqlActuator.update(SQLUtils.getSql("CourseList","update",map,false,""));
    }

    public List<CourseEntity> selectAll() throws Exception {
        return mysqlActuator.getForList(CourseEntity.class, "SELECT * from CourseList");
    }

    public CourseEntity selectByID(String courseName) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.get(CourseEntity.class,"SELECT * FROM CourseList where courseName LIKE '"+courseName+"'");
    }
}

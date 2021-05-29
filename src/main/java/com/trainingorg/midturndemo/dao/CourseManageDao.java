package com.trainingorg.midturndemo.dao;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.bean.Entity.CourseEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CourseManageDao {

    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public void addCourse(String coursename) throws SQLException {
        mysqlActuator.update("INSERT INTO CourseList(courseName) Values('" + coursename + "')");
    }

    public void deleteCourse(String coursename) throws SQLException {
        mysqlActuator.update("DELETE FROM CourseList where courseName='" + coursename + "'");
    }

    public void editCourse(String coursename,String cost,String state) throws SQLException {
        mysqlActuator.update("UPDATE CourseList SET coursename='"+coursename+"',courseCost="+cost+",courseState="+state+" where coursename='"+coursename+"'");
    }

    public List<CourseEntity> selectAll() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.getForList(CourseEntity.class, "SELECT * from CourseList");
    }

    public List<CourseEntity> selectByID(String courseName) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.getForList(CourseEntity.class,"SELECT * FROM CourseList where courseName LIKE '"+courseName+"'");
    }
}

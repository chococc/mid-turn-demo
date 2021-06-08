package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.bean.Entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LoginUserManagerDao {

    protected MysqlActuator mysqlActuator = new MysqlActuator();

    public void adduser(String username, String password, Timestamp timestamp) throws SQLException {
        mysqlActuator.update("INSERT INTO Users(username,password,createDate) VALUES('" + username + "','" + password + "','"+timestamp+"',null)");
    }

    public UserEntity selectByID(String id) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return mysqlActuator.get(UserEntity.class,"SELECT * FROM users where username='"+id+"'");
    }

    public boolean existID(String id) throws SQLException {
        return mysqlActuator.getResultSet_Select("SELECT * from users where username='"+id+"'").next();
    }

    public void resetPassword(String username,String password) throws SQLException {
        mysqlActuator.update("UPDATE Users SET password = '"+password+"'where Username='"+username+"'");
    }
}

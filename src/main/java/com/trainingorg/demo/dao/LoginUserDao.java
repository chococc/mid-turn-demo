package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.SQLUtils;
import com.trainingorg.demo.bean.Entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class LoginUserDao {

    protected MysqlActuator mysqlActuator = new MysqlActuator();

    public void adduser(String username, String password, Timestamp timestamp) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("createDate",timestamp.toString());
        //mysqlActuator.update("INSERT INTO Users(username,password,createDate) VALUES('" + username + "','" + password + "','"+timestamp+"')");
        mysqlActuator.update(SQLUtils.getSql("Users","insert",map,false,""));
    }

    public UserEntity selectByID(String id) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return mysqlActuator.get(UserEntity.class,"SELECT * FROM users where username='"+id+"'");
    }

    public boolean existID(String id) throws SQLException {
        return mysqlActuator.getResultSet_Select("SELECT * from users where username='"+id+"'").next();
    }

    public void resetPassword(String username,String password) throws Exception {
        Map<String, String> map=new HashMap<>();
        map.put("password",password);
        map.put("Key_Username",username);
        mysqlActuator.update(SQLUtils.getSql("users","update",map,false,""));
    }
}

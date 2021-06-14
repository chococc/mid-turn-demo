package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.NoToken;
import com.trainingorg.demo.Util.SQLUtils;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginUserDao {

    protected MysqlActuator mysqlActuator = new MysqlActuator();

    public void adduser(String username, String password, Timestamp timestamp) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("createDate",timestamp.toString());
        //mysqlActuator.update("INSERT INTO Users(username,password,createDate) VALUES('" + username + "','" + password + "','"+timestamp+"')");
        mysqlActuator.update(SQLUtils.getSql("Users","insert",map,false,null));
    }

    public UserEntity selectByID(String id) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return mysqlActuator.get(UserEntity.class,"SELECT * FROM users where username='"+id+"'");
    }

    public boolean existID(String id) throws SQLException {
        return mysqlActuator.getResultSet_Select("SELECT * from users where username='"+id+"'").next();
    }

    public void resetPassword(String username,String password) throws Exception {
        Map<String, Object> map=new HashMap<>();
        map.put("password",password);
        map.put("Key_Username",username);
        mysqlActuator.update(SQLUtils.getSql("users","update",map,false,null));
    }

    public void delete(String username) throws SQLException {
        mysqlActuator.update("DELETE FROM Users where username='" + username + "'");
    }

    public void deleteByToken() throws SQLException, NoToken {
        Token token=new Token();
        String username=token.Token2Username();
        mysqlActuator.update("DELETE FROM Users where username='" + username + "'");
    }
    public void editUsers(String username,String name,String identity,String phone,String Org) throws Exception{
        //mysqlActuator.update("UPDATE Users SET NAME='" + name + "',identify='" + identity + "',telephone='" + phone + "',ORG='" + Org + "' where username='" + username + "'");
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("identify",identity);
        map.put("telephone",phone);
        map.put("Org",Org);
        map.put("Key_username",username);
        mysqlActuator.update(SQLUtils.getSql("Users","update",map,false,null));
    }

    public void editUsers_Token(String name,String identity,String phone,String Org) throws Exception{
        Token token=new Token();
        String username=token.Token2Username();
        //mysqlActuator.update("UPDATE Users SET NAME='" + name + "',identify='" + identity + "',telephone='" + phone + "',ORG='" + Org + "' where username='" + editUser_username + "'");
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("identify",identity);
        map.put("telephone",phone);
        map.put("Org",Org);
        map.put("Key_username",username);
        mysqlActuator.update(SQLUtils.getSql("Users","update",map,false,null));
    }

    public List<UserEntity> selectAll() throws Exception{
        return mysqlActuator.getForList(UserEntity.class, "SELECT * from Users");
    }

    public UserEntity selectAllByID(String username) throws Exception{
        return mysqlActuator.get(UserEntity.class, "SELECT * from Users where username='"+username+"'");
    }
}

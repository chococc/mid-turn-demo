package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.Util.TimeStamp;
import com.trainingorg.midturndemo.bean.Entity.UserEntity;
import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.Util.Token;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service

public class UserService {

    protected MysqlActuator mysqlActuator = new MysqlActuator();
    protected HttpRequest httpRequest=new HttpRequest();

    public HttpRequest addUserService(String username, String password) {
        Timestamp timestamp= new TimeStamp().getNowTimestamp();
        try {
            if (!mysqlActuator.getResultSet_Select("SELECT * FROM Users where username='" + username + "'").next()) {
                try {
                    mysqlActuator.update("INSERT INTO Users(username,password,createdate) VALUES('" + username + "','" + password + "','"+timestamp+"',null)");
                    httpRequest.setRequestCode(200);
                    httpRequest.setRequestMessage("用户注册成功");
                } catch (Exception e) {
                    httpRequest.setRequestCode(201);
                    httpRequest.setRequestMessage("用户注册失败");
                }
            } else {
                httpRequest.setRequestCode(202);
                httpRequest.setRequestMessage("用户名已存在");
            }
            httpRequest.setRequestData(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest deleteUserService(String username) {
        try {
            if (mysqlActuator.getResultSet_Select("SELECT * FROM Users where username='" + username + "'").next()) {
                try {
                    mysqlActuator.update("DELETE FROM Users where Username='" + username + "'");
                    httpRequest.setRequestCode(200);
                    httpRequest.setRequestMessage("用户删除成功");
                } catch (Exception e) {
                    httpRequest.setRequestCode(203);
                    httpRequest.setRequestMessage("用户删除失败");
                    System.out.println(e.getMessage());
                }
            } else {
                httpRequest.setRequestCode(204);
                httpRequest.setRequestMessage("用户删除不存在");
            }
            httpRequest.setRequestData(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest deleteUserByTokenService() {
        String Token = new Token().getToken_Cookie();
        try {
            mysqlActuator.update("DELETE FROM Users where TokenId='" + Token + "'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户删除成功");
        } catch (Exception e) {
            httpRequest.setRequestCode(203);
            httpRequest.setRequestMessage("用户删除失败");
            System.out.println(e.getMessage());
        }
        httpRequest.setRequestData(null);
        return httpRequest;
    }

    public HttpRequest editUsers_service(String username, String name, String identity, String phone, String Org) {
        try {
            mysqlActuator.update("UPDATE Users SET NAME='" + name + "',identify='" + identity + "',telephone='" + phone + "',ORG='" + Org + "' where username='" + username + "'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户信息修改成功");
        } catch (Exception e) {
            httpRequest.setRequestCode(204);
            httpRequest.setRequestMessage("用户信息修改失败");
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editUsersCustomerService(String name, String identity, String phone, String Org) {

        Token token = new Token();
        String edituser = token.getToken_Cookie();

        if (edituser == null) {
            httpRequest.setRequestCode(102);
            httpRequest.setRequestMessage("当前为未登入状态");
        } else {
            try {
                String edituser_username = token.Token2Username(edituser);
                mysqlActuator.update("UPDATE Users SET NAME='" + name + "',identify='" + identity + "',telephone='" + phone + "',ORG='" + Org + "' where username='" + edituser_username + "'");
                httpRequest.setRequestCode(200);
                httpRequest.setRequestMessage("用户信息修改成功");
            } catch (Exception e) {
                httpRequest.setRequestCode(204);
                httpRequest.setRequestMessage("用户信息修改失败");
                System.out.println(e.getMessage());
            }
        }
        return httpRequest;
    }

    public HttpRequest selectUserSelectAll() {
        try {
            httpRequest.setRequestData(new MysqlActuator().getForList(UserEntity.class, "SELECT * from Users"));
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (Exception e){
            httpRequest.setRequestCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }

    public HttpRequest selectUserSelectByIDService(String username){
        try {
            httpRequest.setRequestData(new MysqlActuator().getForList(UserEntity.class, "SELECT * from Users where username='"+username+"'"));
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (Exception e){
            httpRequest.setRequestCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }
}
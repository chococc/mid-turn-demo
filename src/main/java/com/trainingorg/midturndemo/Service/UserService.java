package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.bean.HttpRequest;
import com.trainingorg.midturndemo.bean.MysqlActuator;
import com.trainingorg.midturndemo.bean.Token;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service

public class UserService {

    protected MysqlActuator mysqlActuator = new MysqlActuator();
    protected HttpRequest httpRequest=new HttpRequest();

    public HttpRequest addUser_service(String username, String password) {
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateTime = Timestamp.valueOf(simpleDate.format(date));
        try {
            if (!mysqlActuator.getResultSet_Select("SELECT * FROM Users where username='" + username + "'").next()) {
                try {
                    mysqlActuator.update("INSERT INTO Users VALUES('" + username + "','" + password + "',null)");
                    mysqlActuator.update("INSERT INTO UsersInfo VALUES('" + username + "',null,null,null,'" + dateTime + "',null)");
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

    public HttpRequest deleteUser_service(String username) {
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

    public HttpRequest deleteUserByToken_service() {
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
            mysqlActuator.update("UPDATE UsersInfo SET NAME='" + name + "',identify='" + identity + "',telephone='" + phone + "',ORG='" + Org + "' where username='" + username + "'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户信息修改成功");
        } catch (Exception e) {
            httpRequest.setRequestCode(204);
            httpRequest.setRequestMessage("用户信息修改失败");
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editUsers_Customer_service(String name,String identity,String phone,String Org) {
        Token token = new Token();
        String edituser = token.getToken_Cookie();
        if (edituser == null) {
            httpRequest.setRequestCode(102);
            httpRequest.setRequestMessage("当前为未登入状态");
        } else {
            try {
                String edituser_username = token.Token2Username(edituser);
                mysqlActuator.update("UPDATE UsersInfo SET NAME='" + name + "',identify='" + identity + "',telephone='" + phone + "',ORG='" + Org + "' where username='" + edituser_username + "'");
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
}
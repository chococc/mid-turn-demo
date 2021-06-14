package com.trainingorg.demo.Service;

import com.trainingorg.demo.Util.TimeStamp;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.dao.LoginUserDao;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service

public class UserService {

    protected LoginUserDao loginUserDao =new LoginUserDao();
    protected MysqlActuator mysqlActuator = new MysqlActuator();
    protected HttpRequest httpRequest=new HttpRequest();

    public HttpRequest addUserService(String username, String password) {
        Timestamp timestamp= new TimeStamp().getNowTimestamp();
        try {
            if (!loginUserDao.existID(username)) {
                try {
                    loginUserDao.adduser(username,password,timestamp);
                    httpRequest.setCode(200);
                    httpRequest.setRequestMessage("用户注册成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    httpRequest.setCode(201);
                    httpRequest.setRequestMessage("用户注册失败");
                }
            } else {
                httpRequest.setCode(202);
                httpRequest.setRequestMessage("用户名已存在");
            }
            httpRequest.setData(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest deleteUserService(String username) {
        try {
            if (loginUserDao.existID(username)) {
                try {
                    mysqlActuator.update("DELETE FROM Users where Username='" + username + "'");
                    httpRequest.setCode(200);
                    httpRequest.setRequestMessage("用户删除成功");
                } catch (Exception e) {
                    httpRequest.setCode(203);
                    httpRequest.setRequestMessage("用户删除失败");
                    System.out.println(e.getMessage());
                }
            } else {
                httpRequest.setCode(204);
                httpRequest.setRequestMessage("用户删除不存在");
            }
            httpRequest.setData(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest deleteUserByTokenService() {
        String Token = new Token().getToken_Cookie();
        try {
            mysqlActuator.update("DELETE FROM Users where TokenId='" + Token + "'");
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户删除成功");
        } catch (Exception e) {
            httpRequest.setCode(203);
            httpRequest.setRequestMessage("用户删除失败");
            e.printStackTrace();
        }
        httpRequest.setData(null);
        return httpRequest;
    }

    public HttpRequest editUsers_service(String username, String name, String identity, String phone, String Org) {
        try {
            loginUserDao.editUsers(username,name,identity,phone,Org);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户信息修改成功");
        } catch (Exception e) {
            httpRequest.setCode(204);
            httpRequest.setRequestMessage("用户信息修改失败");
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editUsersCustomerService(String name, String identity, String phone, String Org) {
            try {
                loginUserDao.editUsers_Token(name,identity,phone,Org);
                httpRequest.setCode(200);
                httpRequest.setRequestMessage("用户信息修改成功");
            } catch (Exception e) {
                httpRequest.setCode(204);
                httpRequest.setRequestMessage("用户信息修改失败");
                System.out.println(e.getMessage());
            }
        return httpRequest;
    }

    public HttpRequest selectUserSelectAll() {
        try {
            httpRequest.setData(loginUserDao.selectAll());
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (Exception e){
            httpRequest.setCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }

    public HttpRequest selectUserSelectByIDService(String username){
        try {
            httpRequest.setData(loginUserDao.selectAllByID(username));
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (Exception e){
            httpRequest.setCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }
}
package com.trainingorg.demo.Service.Impl;

import com.trainingorg.demo.Service.UserService;
import com.trainingorg.demo.Util.NoToken;
import com.trainingorg.demo.Util.TimeStamp;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.dao.LoginUserDao;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service

public class UserServiceImpl implements UserService {

    protected LoginUserDao loginUserDao =new LoginUserDao();
    protected MysqlActuator mysqlActuator = new MysqlActuator();

    public HttpRequest add(String username, String password) {
        HttpRequest httpRequest=new HttpRequest();
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

    public HttpRequest delete(String username) {
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("orgManager");
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
        } catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest deleteUserByToken() {
        HttpRequest httpRequest=new HttpRequest();
        try {
            loginUserDao.deleteByToken();
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户删除成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入");
            return httpRequest;
        } catch (Exception e) {
            httpRequest.setCode(203);
            httpRequest.setRequestMessage("用户删除失败");
            e.printStackTrace();
        }
        httpRequest.setData(null);
        return httpRequest;
    }

    public HttpRequest editUsers(String username, String name, String identity, String phone, String Org) {
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("orgManager");
            loginUserDao.editUsers(username,name,identity,phone,Org);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户信息修改成功");
        } catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e) {
            httpRequest.setCode(204);
            httpRequest.setRequestMessage("用户信息修改失败");
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }

    public HttpRequest editUsersCustomer(String name, String phone, String Org) {
        HttpRequest httpRequest=new HttpRequest();
            try {
                loginUserDao.editUsers_Token(name,phone,Org);
                httpRequest.setCode(200);
                httpRequest.setRequestMessage("用户信息修改成功");
            } catch (NoToken n) {
                n.printStackTrace();
                httpRequest.setCode(100);
                httpRequest.setRequestMessage("用户未登入");
                return httpRequest;
            }catch (Exception e) {
                httpRequest.setCode(204);
                httpRequest.setRequestMessage("用户信息修改失败");
                System.out.println(e.getMessage());
            }
        return httpRequest;
    }

    public HttpRequest selectAll() {
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("OrgManager");
            httpRequest.setData(loginUserDao.selectAll());
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非机构管理员账号.");
            return httpRequest;
        }catch (Exception e){
            httpRequest.setCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }

    public HttpRequest selectByID(String username){
        HttpRequest httpRequest=new HttpRequest();
        try {
            new Token().IdentityCheck("orgManager");
            httpRequest.setData(loginUserDao.selectAllByID(username));
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户信息拉取成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        }catch (Exception e){
            httpRequest.setCode(104);
            httpRequest.setRequestMessage("拉取用户信息失败");
            e.printStackTrace();
        }
        return httpRequest;
    }
}
package com.trainingorg.demo.Service.Impl;

import com.trainingorg.demo.Service.LoginService;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.*;
import com.trainingorg.demo.dao.LoginUserDao;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    protected HttpRequest httpRequest=new HttpRequest();
    protected LoginUserDao loginUserDao=new LoginUserDao();

    public HttpRequest login(String username, String password){
        String Token;
    try {
        if (loginUserDao.selectByActiveID(username).getPassword().equals(password)) {
            Token = new Token().getToken_login(username, password);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("登入成功");
            httpRequest.setData(Token);
        } else {
            httpRequest.setCode(202);
            httpRequest.setRequestMessage("密码错误");
        }
    }catch(Exception e){
        httpRequest.setCode(201);
        httpRequest.setRequestMessage("用户不存在");
    }
        return httpRequest;
    }

    public HttpRequest check_login(){

        String Token=new Token().getToken_Cookie();

        if (Token!=null) {
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("当前请求已认证");
            httpRequest.setData(Token);
        }else{
            httpRequest.setCode(102);
            httpRequest.setRequestMessage("当前为未登入状态");
            httpRequest.setData(null);
        }
        return httpRequest;
    }

    public HttpRequest logout(){

        String token = new Token().getToken_Cookie();

        if(token!=null){
            new Token().TokenDeleter(token);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("用户成功登出");
        }else{
            httpRequest.setCode(102);
        }
        httpRequest.setData(null);

        return httpRequest;
    }

    public HttpRequest reset_password(String username,String password){
        try{
            new LoginUserDao().resetPassword(username,password);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("已成功重置密码");
        }catch (Exception e){
            httpRequest.setCode(103);
            httpRequest.setRequestMessage("重置密码失败");
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }
}
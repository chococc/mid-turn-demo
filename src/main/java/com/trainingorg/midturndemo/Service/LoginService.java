package com.trainingorg.midturndemo.Service;

import com.trainingorg.midturndemo.Util.MysqlActuator;
import com.trainingorg.midturndemo.Util.RequestMessage;
import com.trainingorg.midturndemo.Util.Token;
import com.trainingorg.midturndemo.bean.*;
import com.trainingorg.midturndemo.dao.LoginUserManagerDao;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    protected HttpRequest httpRequest=new HttpRequest();

    public HttpRequest user_login_service(String username, String password){

        String Token;

        if(new Authenticator().Confirmer(username,password).getMessage().equals(new RequestMessage().LoginMessage_Success().getMessage())){

            Token=new Token().getToken_login(username,password);

            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("登入成功");
            httpRequest.setRequestData(Token);
        }
        else{
            httpRequest.setRequestCode(101);
            httpRequest.setRequestMessage("登入失败");
            httpRequest.setRequestData(new Token().getToken_login(username,password));
        }
        return httpRequest;
    }

    public HttpRequest check_login_service(){

        String Token=new Token().getToken_Cookie();

        if (Token!=null) {
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("当前请求已认证");
            httpRequest.setRequestData(Token);
        }else{
            httpRequest.setRequestCode(102);
            httpRequest.setRequestMessage("当前为未登入状态");
            httpRequest.setRequestData(null);
        }
        return httpRequest;
    }

    public void check_login_service_strong() throws check_login_service_strong_Exception {
        String Token=new Token().getToken_Cookie();
        if(Token==null)
            throw new check_login_service_strong_Exception();
    }

    public HttpRequest logout_service(){

        String token = new Token().getToken_Cookie();

        if(token!=null){
            new Token().TokenDeleter(token);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("用户成功登出");
        }else{
            httpRequest.setRequestCode(102);
        }
        httpRequest.setRequestData(null);

        return httpRequest;
    }

    public HttpRequest user_reset_password(String username,String password){
        try{
            new LoginUserManagerDao().resetPassword(username,password);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("已成功重置密码");
        }catch (Exception e){
            httpRequest.setRequestCode(103);
            httpRequest.setRequestMessage("重置密码失败");
            System.out.println(e.getMessage());
        }
        return httpRequest;
    }
}

class check_login_service_strong_Exception extends Exception{
    public check_login_service_strong_Exception() {
        super("当前请求未登入");
    }
}
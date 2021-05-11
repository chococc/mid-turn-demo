package com.trainingorg.midturndemo.Util;

import com.trainingorg.midturndemo.bean.Authenticator;

import java.sql.ResultSet;
import java.util.UUID;

public class Token {

    protected Cookies cookies=new Cookies();
    protected MysqlActuator mysqlActuator=new MysqlActuator();
    protected String token;

    /**
     * TokenGenerator
     * param username(String) password(String)
     * return RequestMessage
     * param username,password
     * return RequestMessage
     * System Exception Code 02
     */

    public RequestMessage TokenGenerator(String username,String password) {
        RequestMessage RM;
        if(new Authenticator().Confirmer(username, password).getMessage().equals(new RequestMessage().LoginMessage_Success().getMessage())) {
            RM=new RequestMessage().Token_Success();
            token = UUID.randomUUID().toString().replaceAll("-", "");
            //System.out.println(token);
            try {
                mysqlActuator.update("UPDATE Users SET TokenId ='" + token + "' WHERE Username =" + username);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            RM = new Authenticator().Confirmer(username, password);
        }
        return RM;
    }
    //TokenGenerator test Interface.
    public RequestMessage TokenGenerator_test(String username,String password){
        return TokenGenerator(username, password);
    }

    /**
     * TokenPrinter
     * param username,password
     * @return Token(String)
     */

    public String getToken_login(String username,String password){
        if(TokenGenerator(username,password).getMessage().equals(new RequestMessage().Token_Success().getMessage())){
            cookies.setCookie(token);
            return token;
        }
        else{
            cookies.deleteCookie("Token");
            return TokenGenerator(username,password).getMessageCode();
        }
    }
    //Token printer test Interface.
    public String getToken_test(String username,String password){
        return getToken_login(username,password);
    }

    public String getToken_Cookie(){
        return cookies.searchCookie("Token");
    }
    //Token printer 4 Cookie test Interface
    public String getToken_Cookie_Test(){
        return getToken_Cookie();
    }
    /**
     * TokenDeleter
     * param
     * return requestMessage
     */

    public RequestMessage TokenDeleter(String Token){
        try {
            mysqlActuator.update("UPDATE Users SET TokenId = null WHERE TokenId = '"+Token+"'");
        }catch (Exception e){
            e.printStackTrace();
        }
        RequestMessage RM = new RequestMessage().Token_Deleted();
        cookies.deleteCookie("Token");
        return RM;
    }
    // TokenDeleter test Interface
    public RequestMessage TokenDeleter_test(String token){
        return TokenDeleter(token);
    }

    public String Token2Username(String Token){
        try {
            ResultSet result = mysqlActuator.getResultSet_Select("SELECT Username FROM Users WHERE TokenId='"+Token+"' AND TokenId is not null");
            if(result.next()){
                return result.getString("Username");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
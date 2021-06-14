package com.trainingorg.demo.bean;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.RequestMessage;

import java.sql.*;

/**
 * Login Data Authenticator.
 * version 1.0
 * date 2021.4.20
 * param username(String) password(String)
 * return Token.RequestMessage
 * System Exception Code 00
 */
public class Authenticator {
    /**
     * Login Data Confirmed.
     * version 1.0
     * date 2020.4.20
     * param  java.sql.connection username password
     * return Token.RequestMessage
     * System Exception Code 01
     */
    public RequestMessage Conformer(String username, String password){
        try {
            ResultSet rs = new MysqlActuator().getResultSet_Select("select * from Users where username = '" + username+"'");
            if(!rs.next()){
                return new RequestMessage().LoginMessage_NoUser();
            }
            String password0 = rs.getString("Password");
            if(!password0.equals(password)){
                return new RequestMessage().LoginMessage_WrongPassword();
            }
        }catch(Exception e){
            return new RequestMessage().LoginMessage_ServiceError(e);
        }
        return new RequestMessage().LoginMessage_Success();
    }

    //Login Data Confirmed Test Interface.
    public RequestMessage Access_Test(String username,String password){
        return Conformer(username,password);
    }

    public Authenticator(){}
}

package com.trainingorg.midturndemo.Util;

public class RequestMessage {

    protected String RMCode;
    protected String message;

    public RequestMessage(){}

    public String getMessageCode(){return RMCode;}
    public String getMessage(){return message;}

    /**
     * Mysql Data Connector Message Group
     * System Message Code 000000
     * To com.trainingorg.midturndemo.bean.Authenticator
     */

    public RequestMessage DataSource_Success(){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000000";
        RM.message="数据库链接成功";
        return RM;
    }

    public RequestMessage DataSource_Error(Exception e){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000001";
        RM.message="数据库链接失败:" + e;
        return RM;
    }
    /**
     * System Login Message Group
     * System Message Code 000001
     * To com.trainingorg.midturndemo.bean.Authenticator
     */
    public RequestMessage LoginMessage_Success(){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000100";
        RM.message="登入成功";
        return RM;
    }

    public RequestMessage LoginMessage_NoUser(){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000101";
        RM.message="用户不存在";
        return RM;
    }

    public RequestMessage LoginMessage_WrongPassword(){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000102";
        RM.message="密码错误";
        return RM;
    }

    public RequestMessage LoginMessage_ServiceError(Exception e){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000103";
        RM.message="登入服务启动失败:"+e;
        return RM;
    }

    /**
     * Token Login message group
     * system message code 000002
     * To com.trainingorg.midturndemo.Util.Token
     */
    public RequestMessage Token_Success(){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000200";
        RM.message="Token生成成功";
        return RM;
    }

    public RequestMessage Token_Deleted(){
        RequestMessage RM=new RequestMessage();
        RM.RMCode="00000201";
        RM.message="Token已被删除";
        return RM;
    }
}

package com.trainingorg.demo.bean;

import lombok.Data;

@Data

public class HttpRequest {
    protected int code;
    protected String msg;
    protected Object data;
    protected int count;

    public void setCode(int Code){this.code =Code;}

    public void setRequestMessage(String Message){this.msg=Message;}

    public void setData(Object Data){this.data =Data;}

    public HttpRequest(){}
}

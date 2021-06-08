package com.trainingorg.demo.bean;

public class HttpRequest {
    @lombok.Getter
    protected int RequestCode;
    @lombok.Getter
    protected String RequestMessage;
    @lombok.Getter
    protected Object RequestData;

    public void setRequestCode(int Code){this.RequestCode=Code;}

    public void setRequestMessage(String Message){this.RequestMessage=Message;}

    public void setRequestData(Object Data){this.RequestData=Data;}

    public HttpRequest(){}
}

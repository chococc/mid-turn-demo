package com.trainingorg.midturndemo.bean.Entity;

public class UserEntity {

    protected String Username;
    protected String Password;
    protected int status;

    public void setUsername(String username){this.Username=username;}
    public String getUsername(){return Username;}
    public void setPassword(String password){this.Password=password;}
    public String getPassword(){return Password;}
    public void setStatus(int status){this.status=status;}
    public int getStatus(){return status;}

    public UserEntity(){}
}

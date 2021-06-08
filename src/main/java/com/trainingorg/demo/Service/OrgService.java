package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.OrgEntity;
import com.trainingorg.demo.bean.Entity.UserEntity;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    HttpRequest httpRequest=new HttpRequest();
    MysqlActuator mysqlActuator=new MysqlActuator();

    public HttpRequest addOrg(String OrgName,String OrgAddress,String managerName,String managerPassword,String telephone,String managerIdentityCard){
        try{
            mysqlActuator.update("Insert into Users(userName,name,password,userState,identify) values('"+OrgName+"','"+managerName+"','"+managerPassword+"',1,'OrgManager')");
            String manager=mysqlActuator.get(UserEntity.class,"SELECT * FROM Users where name='"+managerName+"' and identify='OrgManager'").getUsername();
            mysqlActuator.update("Insert into OrgList(OrgName,address,manager,telephone,managerIdentityCard) values ('"+OrgName+"','"+OrgAddress+"','"+manager+"','"+telephone+"','" + managerIdentityCard+"')");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("机构添加成功 登入用户名:"+OrgName+"登入密码:"+managerPassword);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(701);
            httpRequest.setRequestMessage("机构添加失败");
        }
        return httpRequest;
    }

    public HttpRequest checkOrg(String OrgID,String check,String status){
        try {
            mysqlActuator.update("UPDATE OrgList set check='"+check+"','status="+status+" where OrgID="+OrgID);
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("机构审核状态已更新");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(702);
            httpRequest.setRequestMessage("机构审核状态更新失败");
        }
        return httpRequest;
    }

    public HttpRequest updateMessage(String manager,String telephone,String managerName,String managerIdentityCard){
        try{
            mysqlActuator.update("UPDATE OrgList set manager='"+manager+"',telephone='"+telephone+"',managerName='"+managerName+"',managerIdentityCard='"+managerIdentityCard+"'");
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("机构信息更新成功");
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setRequestCode(703);
            httpRequest.setRequestMessage("机构信息更新失败");
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try{
            httpRequest.setRequestData(JSON.toJSON(mysqlActuator.getForList(OrgEntity.class,"SELECT * from OrgList")));
            httpRequest.setRequestCode(200);
            httpRequest.setRequestMessage("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setRequestData(704);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public int Token2OrgID(){
        try{
            Token token=new Token();
            String username=token.Token2Username();
            return mysqlActuator.get(OrgEntity.class,"SELECT * FROM OrgList where manager="+username).getOrgId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}

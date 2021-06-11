package com.trainingorg.demo.dao;

import com.trainingorg.demo.Util.MysqlActuator;
import com.trainingorg.demo.Util.SQLUtils;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.Entity.OrgEntity;
import com.trainingorg.demo.bean.Entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrgDao {

    protected MysqlActuator mysqlActuator=new MysqlActuator();

    public void addOrg(String OrgName,String OrgAddress,String managerName,String managerPassword,String telephone,String managerIdentityCard) throws Exception {
        //mysqlActuator.update("Insert into Users(userName,name,password,userState,identify) values('"+OrgName+"','"+managerName+"','"+managerPassword+"',1,'OrgManager')");
        Map<String,Object> map=new HashMap<>();
        map.put("userName",OrgName);
        map.put("name",managerName);
        map.put("password",managerPassword);
        map.put("userState",0);
        map.put("identity","OrgManager");
        mysqlActuator.update(SQLUtils.getSql("Users","insert",map,false,""));
        String manager=mysqlActuator.get(UserEntity.class,"SELECT * FROM Users where name='"+managerName+"' and identify='OrgManager'").getUsername();
        //mysqlActuator.update("Insert into OrgList(OrgName,address,manager,telephone,managerIdentityCard) values ('"+OrgName+"','"+OrgAddress+"','"+manager+"','"+telephone+"','" + managerIdentityCard+"')");
        map=new HashMap<>();
        map.put("OrgName",OrgName);
        map.put("address",OrgAddress);
        map.put("manager",manager);
        map.put("telephone",telephone);
        map.put("managerIdentityCard",managerIdentityCard);
        mysqlActuator.update(SQLUtils.getSql("OrgList","insert",map,false,""));
    }

    public void checkOrg(String OrgID,String status) throws SQLException {
        mysqlActuator.update("UPDATE OrgList set check='1','status="+status+" where OrgID="+OrgID);

    }

    public void updateMessage(String manager,String telephone,String managerName,String managerIdentityCard) throws SQLException {
        mysqlActuator.update("UPDATE OrgList set manager='"+manager+"',telephone='"+telephone+"',managerName='"+managerName+"',managerIdentityCard='"+managerIdentityCard+"',check='0'");
    }

    public List<OrgEntity> selectAll() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mysqlActuator.getForList(OrgEntity.class,"SELECT * from OrgList");
    }

    public int Token2OrgID() throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Token token=new Token();
        String username=token.Token2Username();
        return mysqlActuator.get(OrgEntity.class,"SELECT * FROM OrgList where manager="+username).getOrgId();
    }
}

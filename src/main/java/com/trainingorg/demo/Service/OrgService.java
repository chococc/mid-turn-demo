package com.trainingorg.demo.Service;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.OrgDao;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    HttpRequest httpRequest=new HttpRequest();
    OrgDao orgDao=new OrgDao();

    public HttpRequest addOrg(String OrgName,String OrgAddress,String managerName,String managerPassword,String telephone,String managerIdentityCard){
        try{
            orgDao.addOrg(OrgName,OrgAddress,managerName,managerPassword,telephone,managerIdentityCard);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("机构添加成功 登入用户名:"+OrgName+"登入密码:"+managerPassword);
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(701);
            httpRequest.setRequestMessage("机构添加失败");
        }
        return httpRequest;
    }

    public HttpRequest checkOrg(String OrgID){
        try {
            orgDao.checkOrg(OrgID);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("机构审核状态已更新");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(702);
            httpRequest.setRequestMessage("机构审核状态更新失败");
        }
        return httpRequest;
    }

    public HttpRequest updateMessage(String manager,String telephone,String managerName,String managerIdentityCard){
        try{
            orgDao.updateMessage(manager,telephone,managerName,managerIdentityCard);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("机构信息更新成功");
        }catch(Exception e){
            e.printStackTrace();
            httpRequest.setCode(703);
            httpRequest.setRequestMessage("机构信息更新失败");
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try{
            httpRequest.setData(JSON.toJSON(orgDao.selectAll()));
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(704);
            httpRequest.setRequestMessage("查询失败");
        }
        return httpRequest;
    }

    public int Token2OrgID(){
        try{
            return orgDao.Token2OrgID();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}

package com.trainingorg.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.trainingorg.demo.Service.OrgService;
import com.trainingorg.demo.Util.NoToken;
import com.trainingorg.demo.Util.Token;
import com.trainingorg.demo.bean.HttpRequest;
import com.trainingorg.demo.dao.OrgDao;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl implements OrgService {

    HttpRequest httpRequest=new HttpRequest();
    OrgDao orgDao=new OrgDao();

    public HttpRequest add(String OrgName,String OrgAddress,String managerName,String managerPassword,String telephone,String managerIdentityCard){
        try{
            new Token().IdentityCheck("orgManager");
            orgDao.addOrg(OrgName,OrgAddress,managerName,managerPassword,telephone,managerIdentityCard);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("机构添加成功 登入用户名:"+OrgName+"登入密码:"+managerPassword);
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        } catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(701);
            httpRequest.setRequestMessage("机构添加失败");
        }
        return httpRequest;
    }

    public HttpRequest check(String OrgID){
        try {
            new Token().IdentityCheck("orgManager");
            orgDao.checkOrg(OrgID);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("机构审核状态已更新");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        } catch (Exception e){
            e.printStackTrace();
            httpRequest.setCode(702);
            httpRequest.setRequestMessage("机构审核状态更新失败");
        }
        return httpRequest;
    }

    public HttpRequest updateMessage(String telephone,String managerName,String managerIdentityCard){
        try{
            new Token().IdentityCheck("orgManager");
            orgDao.updateMessage(telephone,managerName,managerIdentityCard);
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("机构信息更新成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        } catch(Exception e){
            e.printStackTrace();
            httpRequest.setCode(703);
            httpRequest.setRequestMessage("机构信息更新失败");
        }
        return httpRequest;
    }

    public HttpRequest selectAll(){
        try{
            new Token().IdentityCheck("orgManager");
            httpRequest.setData(JSON.toJSON(orgDao.selectAll()));
            httpRequest.setCode(200);
            httpRequest.setRequestMessage("查询成功");
        }catch (NoToken n) {
            n.printStackTrace();
            httpRequest.setCode(100);
            httpRequest.setRequestMessage("用户未登入,或使用了非管理员账号.");
            return httpRequest;
        } catch (Exception e){
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

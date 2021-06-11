package com.trainingorg.demo.Controller;

import com.trainingorg.demo.Service.OrgService;
import com.trainingorg.demo.bean.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Org")
public class OrgController {

    OrgService orgService=new OrgService();

    @RequestMapping(value="/add")
    public HttpRequest add(@RequestParam(value = "OrgName") String OrgName,@RequestParam(value = "OrgAddress") String OrgAddress,@RequestParam("managerName") String managerName,@RequestParam("managerPassword") String managerPassword,@RequestParam("telephone") String telephone,@RequestParam("managerIdentityCard") String managerIdentityCard){
        return orgService.addOrg(OrgName,OrgAddress,managerName,managerPassword,telephone,managerIdentityCard);
    }

    @RequestMapping(value = "/check")
    public HttpRequest check(@RequestParam("OrgID") String OrgID,@RequestParam("status") String status){
        return orgService.checkOrg(OrgID,status);
    }

    @RequestMapping(value="/update")
    public HttpRequest update(@RequestParam("manager") String manager,@RequestParam("telephone") String telephone,@RequestParam("managerName") String managerName,@RequestParam("managerIdeneityCard") String managerIdentityCard){
        return orgService.updateMessage(manager,telephone,managerName,managerIdentityCard);
    }

    @RequestMapping(value = "/selectAll")
    public HttpRequest selectAll(){return orgService.selectAll();}
}

package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface OrgService {
    HttpRequest add(String OrgName,String OrgAddress,String managerName,String managePassword,String telephone,String managerIdentityCard);
    HttpRequest check(String OrgID);
    HttpRequest updateMessage(String telephone,String managerName,String managerIdentityCard);
    HttpRequest selectAll();
    int Token2OrgID();
}

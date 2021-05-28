package com.trainingorg.midturndemo.bean.Entity;

import lombok.Data;

@Data
public class OrgEntity {

    protected int OrgId;
    protected String OrgName;
    protected String manager;
    protected String managerName;
    protected String managerIdentityCard;
    protected String telephone;
    protected String address;
    protected String check;
    protected int status;

    OrgEntity(){}
}

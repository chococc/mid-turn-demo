package com.trainingorg.midturndemo.bean.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserEntity {

    protected String Username;
    protected String Password;
    protected String name;
    protected String identity;
    protected String telephone;
    protected String CreateDate;
    protected String Org;
    protected int status;

}

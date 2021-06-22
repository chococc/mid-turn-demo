package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface LoginService {
    HttpRequest login(String username,String password);
    HttpRequest check_login();
    HttpRequest logout();
    HttpRequest reset_password(String username,String password);
}

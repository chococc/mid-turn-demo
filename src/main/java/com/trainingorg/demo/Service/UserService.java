package com.trainingorg.demo.Service;

import com.trainingorg.demo.bean.HttpRequest;

public interface UserService {
    HttpRequest add(String username,String password);
    HttpRequest delete(String username);
    HttpRequest deleteUserByToken();
    HttpRequest editUsers(String username,String name,String identity,String phone,String Org);
    HttpRequest editUsersCustomer(String name,String phone,String Org);
    HttpRequest selectAll();
    HttpRequest selectByID(String username);
}

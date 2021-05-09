package com.trainingorg.midturndemo.bean;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Cookies {
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletResponse response;

    {
        assert servletRequestAttributes != null;
        response = servletRequestAttributes.getResponse();
    }

    HttpServletRequest request = servletRequestAttributes.getRequest();

    public void setCookie(String value){
        Cookie cookie=new Cookie("Token",null);
        cookie.setValue(value);
        response.addCookie(cookie);
    }

    public void deleteCookie(String name){
        Cookie cookie=new Cookie(name,null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public Cookie[] getCookie(){
        return request.getCookies();
    }

    public String searchCookie(String name){
        Cookie[] cookies=getCookie();
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
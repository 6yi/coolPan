package com.lzheng.coolpan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginIn
 * @Author 刘正
 * @Date 2019/12/14 14:59
 * @Version 1.0
 * @Description:
 */

@Component
public class LoginIn implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String login = (String) request.getSession().getAttribute("needLogin");
        if (login==null){
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else if (login.equals("1")){
            return true;
        }
        return false;
    }
}

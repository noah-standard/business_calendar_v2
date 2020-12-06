package com.end.demo.auth;

import com.end.demo.lib.Util;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck") == null) {
            Util.errorAdminLogin(request,response);
            return false;
        } else {
            return true;
        }

    }

}
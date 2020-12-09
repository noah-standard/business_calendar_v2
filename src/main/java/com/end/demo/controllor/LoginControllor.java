package com.end.demo.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.WebSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginControllor {

    @GetMapping(value = "logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute("loginCheck");
        session.removeAttribute("userid");
        return "redirect:/";
    }

}

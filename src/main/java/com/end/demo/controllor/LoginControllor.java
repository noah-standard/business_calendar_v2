package com.end.demo.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControllor {

    @RequestMapping(value = "logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}

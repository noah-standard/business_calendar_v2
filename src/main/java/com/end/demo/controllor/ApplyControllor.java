package com.end.demo.controllor;

import com.end.demo.service.UserService;
import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.XhrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accept")
public class ApplyControllor {

    @Autowired
    UserService service;

    @GetMapping("")
    public ModelAndView getAccept(Principal principal) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", "fragments/accept");//html templates
        mv.addObject("templates", "accept");//css
        mv.addObject("pageTitle", "accept");
        mv.addObject("etc", service.getEtc(principal.getName()));
        mv.addObject("session", principal.getName());
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("write.do")
    public String insertCalendar(CalendarVO cVo) {
        System.out.println(cVo);
        service.insertCalendar(cVo);
        return "redirect:/accept";
    }

}

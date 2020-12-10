package com.end.demo.controllor;

import com.end.demo.service.CalService;
import com.end.demo.service.UserService;
import com.end.demo.service.cms.CalendarService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.XhrVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    CalService c_service;

    @Autowired
    PageManageService pageManageService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    CalendarService calendarService;

    @GetMapping("")
    public String getIndex(Model model) {
        System.out.println(pageManageService.selectUserMenu());
        model.addAttribute("holidayList", c_service.getHoliday());
        model.addAttribute("userMenuList",pageManageService.selectUserMenu());
        model.addAttribute("userVacationList",calendarService.selectCalendarList());
        model.addAttribute("page", "fragments/indexPage");//html templates
        model.addAttribute("templates", "indexPage");//css
        model.addAttribute("pageTitle", "index");
        return "index";
    }

    @PostMapping("")
    public String getUser(HttpSession session, @ModelAttribute UserVO userVO) {
        ModelAndView mv = new ModelAndView();
        UserVO requestUserVO = service.getUser(userVO.getUserid());

        if (session != null && userVO != null) {
            boolean matchPw = passwordEncoder.matches(userVO.getPassword(),requestUserVO.getPassword());
            if (matchPw) {
                session.setAttribute("loginCheck", true);
                session.setAttribute("userid", userVO.getUserid());
            }
        }
        return "redirect:";
    }
}

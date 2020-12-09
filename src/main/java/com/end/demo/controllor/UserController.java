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
            boolean matchPw = passwordEncoder.matches(requestUserVO.getPassword(),userVO.getPassword());
            System.out.println(matchPw);
            if (matchPw) {
                session.setAttribute("loginCheck", true);
                session.setAttribute("userid", userVO.getUserid());
            }
        }
        return "redirect:";
    }


    @RequestMapping("/check")
    public ModelAndView getCheck(Principal principal, @RequestParam int page) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", "fragments/check");//html templates
        mv.addObject("templates", "check");//css
        mv.addObject("pageTitle", "check");
        mv.addObject("session", principal.getName());
        mv.addObject("etc", service.getEtc(principal.getName()));
        mv.addObject("maxCountList", service.maxCountList(principal.getName()));
        mv.addObject("list", service.getList(principal.getName(), page));
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String updateDelete(@RequestParam List<Integer> i, int page) {
        service.updateDelete(i);
        return "redirect:check?page=" + page;
    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    public String updateAgree(@RequestParam List<Integer> i, String agree_flag, int page) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("agree_flag", agree_flag);
        param.put("listInteger", i);
        service.updateAgree(param);
        return "redirect:check?page=" + page;
    }

    @RequestMapping(value = "/detail")
    public ModelAndView detailView(int i, Principal principal) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("vo", service.getDetail(i));
        mv.addObject("page", "fragments/detail");//html templates
        mv.addObject("templates", "detail");//css
        mv.addObject("pageTitle", "detail");
        mv.addObject("session", principal.getName());
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView getUpdate(int i, Principal principal) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("vo", service.getDetail(i));
        mv.addObject("page", "fragments/update");//html templates
        mv.addObject("templates", "update");//css
        mv.addObject("pageTitle", "update");
        mv.addObject("session", principal.getName());
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateForm(CalendarVO cvo) {
        service.updateForm(cvo);
        return "redirect:detail?i=" ;
    }
}

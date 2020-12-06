package com.end.demo.controllor.cms;

import com.end.demo.service.cms.AdminLoginService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cms")
public class AdminLoginControllor {

    @Autowired
    AdminLoginService adminLoginService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("admin_login")
    public ModelAndView cmsLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cms/admin_login");
        return mv;
    }

    @RequestMapping(value = "admin_login",method = RequestMethod.POST)
    public ModelAndView cmsLoginProcess(HttpSession session, @RequestParam String userid, @RequestParam String password) {

        ModelAndView mv = new ModelAndView();
        AdminVO adminVO = adminLoginService.getAdmin(userid);

        if (session != null && adminVO != null) {
            boolean matchPw = passwordEncoder.matches(password,adminVO.getPassword());
            if (matchPw) {
                session.setAttribute("adminLoginCheck", true);
                session.setAttribute("adminUserid", adminVO.getUserid());
                mv.setViewName("redirect:calendar/list.do");
            }

        }else{
            mv.setViewName("cms/admin_login");
        }

        return mv;
    }

    @RequestMapping(value = "logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute("adminLoginCheck");
        session.removeAttribute("adminUserid");
        return "redirect:/cms";
    }

}

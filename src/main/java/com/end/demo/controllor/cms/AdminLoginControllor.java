package com.end.demo.controllor.cms;

import com.end.demo.service.cms.AdminLoginService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/cms")
public class AdminLoginControllor {

    @Autowired
    AdminLoginService adminLoginService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("admin_login")
    public String cmsLogin() {
        return "cms/admin_login";
    }

    @PostMapping("admin_login")
    public String cmsLoginProcess(HttpSession session, @Valid @ModelAttribute AdminVO adminVO, BindingResult bindingResult) {
        AdminVO chkAdminVO = adminLoginService.getAdmin(adminVO.getUserid());

        if (session != null && chkAdminVO != null) {
            boolean matchPw = passwordEncoder.matches(adminVO.getPassword(),chkAdminVO.getPassword());
            if (matchPw) {
                session.setAttribute("adminLoginCheck", true);
                session.setAttribute("adminUserid", chkAdminVO.getUserid());
                return "redirect:calendar/list";
            }
        }
        return "cms/admin_login";
    }

    @GetMapping("logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute("adminLoginCheck");
        session.removeAttribute("adminUserid");
        return "redirect:/cms";
    }

}

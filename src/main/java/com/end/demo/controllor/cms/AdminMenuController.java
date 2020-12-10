package com.end.demo.controllor.cms;

import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.vo.AdminMenuVO;
import com.end.demo.vo.param.PageManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cms/admin_config")
public class AdminMenuController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AdminMenuService adminMenuService;

    @ModelAttribute
    public void pageInfo(Model model){
        model.addAttribute("menu", "admin_config");
        model.addAttribute("page", "menu_admin");
    }

    @GetMapping("menu")
    public String getConfig(Model model) {
//        model.addAttribute("menu", "admin_config");
//        model.addAttribute("page", "menu_admin");
        model.addAttribute("menu_list",adminMenuService.selectAdminMenu());
        return "cms/template";
    }

    @PostMapping("menu")
    public String postConfig(@ModelAttribute PageManageVO pageManageVO) {
        adminMenuService.insertAdminMenu(pageManageVO);
        return "redirect:/cms/config";
    }

    @GetMapping("menu/edit")
    public String editMenu(@ModelAttribute AdminMenuVO adminMenuVO) {
//        model.addAttribute("menu", "admin_config");
//        model.addAttribute("page", "menu_admin");
        adminMenuService.updateAdminMenu(adminMenuVO);
        return "redirect:menu";
    }
}
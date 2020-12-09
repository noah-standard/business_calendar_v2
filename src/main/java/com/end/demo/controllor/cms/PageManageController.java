package com.end.demo.controllor.cms;

import com.end.demo.lib.Util;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.AdminMenuVO;
import com.end.demo.vo.UserMenuVO;
import com.end.demo.vo.param.PageManageVO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/cms/page_manage")
public class PageManageController {

    @Autowired
    PageManageService pageManageService;

    @GetMapping("")
    public String userPageManage(Model model) {
        model.addAttribute("menu_list",pageManageService.selectUserMenu());
        model.addAttribute("menu", "user");
        model.addAttribute("page", "menu_user");
        return "cms/template";
    }

    @PostMapping("")
    public String writeUserPage(@ModelAttribute PageManageVO pageManageVO) {
        pageManageService.insertUserMenu(pageManageVO);
        return "redirect:/cms/page_manage";
    }

    @GetMapping(value={"/edit.do"} ,produces = "application/json; charset=utf8")
    public String editUserMenu(@ModelAttribute UserMenuVO userMenuVO) {
        pageManageService.updateUserMenu(userMenuVO);
        return "redirect:/cms/page_manage";
    }

}
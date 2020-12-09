package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.AdminMenuVO;
import com.end.demo.vo.UserMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cms/page_manage/ajax")
public class PageManageRestController {

    @Autowired
    PageManageService pageManageService;

    @GetMapping("")
    public UserMenuVO getAdminMenu(@RequestParam String node) {
        return pageManageService.getUserMenu(node);
    }

    @PostMapping("")
    public ModelAndView updateAdminMenuOne(UserMenuVO userMenuVO) {
        ModelAndView modelAndView = new ModelAndView();
        pageManageService.updateUserMenuOne(userMenuVO);
        modelAndView.setViewName("redirect:/cms/page_manage");
        return modelAndView;
    }

    @GetMapping("/delete.do")
    public ModelAndView deleteAdminMenuOne(@RequestParam int idx) {
        ModelAndView modelAndView = new ModelAndView();
        pageManageService.deleteUserMenuOne(idx);
        modelAndView.setViewName("redirect:/cms/page_manage");
        return modelAndView;
    }

}

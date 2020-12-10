package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.BBSManageService;
import com.end.demo.service.cms.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BBSManageRestControllor {

    @Autowired
    MemberService memberService;

    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/cms/ajax/bbsManage")
    public ModelAndView BBSManageAjax(@RequestParam(required = false) String allow){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allow",allow);
        modelAndView.addObject("memList",memberService.selectMemLevel());
        modelAndView.setViewName("bbs_manage_write_ajax");
        return modelAndView;
    }

    @GetMapping("/cms/ajax/title")
    public String BBSManageDepth(@RequestParam(required = false) String code){
        String title = adminMenuService.getTitleMenu(code);
        return title;
    }

}

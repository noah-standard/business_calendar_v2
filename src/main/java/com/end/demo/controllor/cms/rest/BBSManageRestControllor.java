package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BBSManageRestControllor {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/cms/ajax/bbsManage")
    public ModelAndView BBSManageAjax(@RequestParam(required = false) String allow){
        ModelAndView mv = new ModelAndView();
        mv.addObject("allow",allow);
        mv.addObject("memList",memberService.selectMemLevel());
        mv.setViewName("bbs_manage_write_ajax");
        return mv;
    }
}

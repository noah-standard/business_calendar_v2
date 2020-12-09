package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BBSManageRestControllor {

    @Autowired
    MemberService memberService;

    @GetMapping("/cms/ajax/bbsManage")
    public ModelAndView BBSManageAjax(@RequestParam(required = false) String allow){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allow",allow);
        modelAndView.addObject("memList",memberService.selectMemLevel());
        modelAndView.setViewName("bbs_manage_write_ajax");
        return modelAndView;
    }
}

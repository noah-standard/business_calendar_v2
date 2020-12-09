package com.end.demo.controllor.cms.rest;

import com.end.demo.service.BBSDataService;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BBSDataRestControllor {

    @Autowired
    BBSDataService bbsDataService;

    @GetMapping("/cms/ajax/bbs_cnt")
    public int getBBSCnt(@RequestParam(required = false) String code){

        int bbsCnt = bbsDataService.getBBSCnt(code);

        return bbsCnt;
    }

}

package com.end.demo.controllor.cms;

import com.end.demo.service.cms.BBSManageService;
import com.end.demo.vo.BBSManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/bbs_manage")
public class BBSManageController {

    @Autowired
    BBSManageService bbsManageService;

    @RequestMapping("")
    public ModelAndView BBSManageList(@RequestParam(required = false) String mode) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("menu","user");
        mv.addObject("page", "bbs_manage");
        mv.addObject("totalBBS",bbsManageService.getTotalBBSManage());
        mv.addObject("BBSManageList",bbsManageService.selectBBSManage());
        mv.addObject("BBSManageGroupList",bbsManageService.selectGroupBBSManage());
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping("write")
    public ModelAndView BBSManageWrite() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("menu","user");
        mv.addObject("page", "bbs_manage_write");
        mv.addObject("action", "write.do");
        mv.addObject("BBSManageList",bbsManageService.selectBBSManage());
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = {"write.do"}, method = RequestMethod.POST)
    public String BBSManageWriteProcess(BBSManageVO bbsManageVO) {
        bbsManageService.insertBBSManage(bbsManageVO);
        return "redirect:cms/bbs_manage";
    }

    @RequestMapping("edit")
    public ModelAndView BBSManageEdit(@RequestParam(required = false) String idx) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("bbsObject", bbsManageService.getBBSManage(idx));
        mv.addObject("menu","user");
        mv.addObject("page", "bbs_manage_write");
        mv.addObject("action", "edit.do");
        mv.addObject("BBSManageList",bbsManageService.selectBBSManage());
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = {"edit.do"}, method = RequestMethod.POST)
    public String BBSManageEditProcess(BBSManageVO bbsManageVO) {
        bbsManageService.updateBBSManage(bbsManageVO);
        return "redirect:cms/bbs_manage";
    }

    @RequestMapping(value = {"delete.do"}, method = RequestMethod.POST)
    public String BBSManageDeleteProcess(@RequestParam(required = false) String idx) {
        bbsManageService.deleteBBSManage(idx);
        return "redirect:cms/bbs_manage";
    }

}
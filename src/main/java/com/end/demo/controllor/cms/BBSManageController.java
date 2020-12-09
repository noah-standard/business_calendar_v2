package com.end.demo.controllor.cms;

import com.end.demo.service.cms.BBSManageService;
import com.end.demo.vo.BBSManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms/bbs_manage")
public class BBSManageController {

    @Autowired
    BBSManageService bbsManageService;

    @ModelAttribute
    public void pageInfo(Model model){
        model.addAttribute("menu","user");
        model.addAttribute("BBSManageList",bbsManageService.selectBBSManage());
    }

    @GetMapping("")
    public String BBSManageList(@RequestParam(required = false) String mode,Model model) {
        model.addAttribute("page", "bbs_manage");
        model.addAttribute("totalBBS",bbsManageService.getTotalBBSManage());
        model.addAttribute("BBSManageGroupList",bbsManageService.selectGroupBBSManage());
        return "cms/template";
    }

    @GetMapping("write")
    public String BBSManageWrite(Model model) {
        model.addAttribute("page", "bbs_manage_write");
        model.addAttribute("action", "write.do");
        return "cms/template";
    }

    @PostMapping("write.do")
    public String BBSManageWriteProcess(@ModelAttribute BBSManageVO bbsManageVO) {
        bbsManageService.insertBBSManage(bbsManageVO);
        return "redirect:cms/bbs_manage";
    }

    @GetMapping("edit")
    public String BBSManageEdit(@RequestParam(required = false) String idx,Model model) {
        model.addAttribute("bbsObject", bbsManageService.getBBSManage(idx));
        model.addAttribute("page", "bbs_manage_write");
        model.addAttribute("action", "edit.do");
        return "cms/template";
    }

    @PostMapping("edit.do")
    public String BBSManageEditProcess(BBSManageVO bbsManageVO) {
        bbsManageService.updateBBSManage(bbsManageVO);
        return "redirect:cms/bbs_manage";
    }

    @PostMapping("delete.do")
    public String BBSManageDeleteProcess(@RequestParam(required = false) String idx) {
        bbsManageService.deleteBBSManage(idx);
        return "redirect:cms/bbs_manage";
    }

}
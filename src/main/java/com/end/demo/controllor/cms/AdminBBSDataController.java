package com.end.demo.controllor.cms;


import com.end.demo.lib.Pager;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.BBSManageService;
import com.end.demo.service.cms.AdminBBSDataService;
import com.end.demo.vo.BBSManageVO;
import com.end.demo.vo.BBSDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cms/bbs")
public class AdminBBSDataController {

    @Autowired
    AdminBBSDataService adminBbsDataService;

    @Autowired
    BBSManageService bbsManageService;

    @Autowired
    AdminMenuService adminMenuService;

    @RequestMapping("list")
    public ModelAndView BBSDataList(@RequestParam(required = false) String code
            , @RequestParam(required = false) String mode
            , @RequestParam(defaultValue = "1") int curPage
            , @RequestParam(defaultValue = "all") String search_order
            , @RequestParam(defaultValue = "") String keyword
            , @RequestParam(required = false) String idx
            , @RequestParam(defaultValue = "0") int list_scale
            , @RequestParam(defaultValue = "0") int list_order) {
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        // 레코드 갯수 계산
        int count = adminBbsDataService.countBBSData(search_order, keyword, code);
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = Integer.parseInt(bbsManageVO.getList_page());
        System.out.println("list_scale:" + list_scale);
        int listCnt = 0;
        if (list_scale == 0) {
            listCnt = Integer.parseInt(bbsManageVO.getList_height());
        } else {
            listCnt = list_scale;
        }

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, curPage, listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();
        List<BBSDataVO> bbsListVO = adminBbsDataService.selectBBSData(start, end, search_order, keyword, code, list_order);

        ModelAndView mv = new ModelAndView();
        mv.addObject("menuList", adminMenuService.selectAdminMenu());
        mv.addObject("mst_bbs", bbsManageVO);
        mv.addObject("bbsList", bbsListVO);
        mv.addObject("bbs_count", count);
        mv.addObject("search_order", search_order);
        mv.addObject("keyword", keyword);
        mv.addObject("pager", pager);
        mv.addObject("bbs_template", "bbs_list");
        mv.addObject("bbs_mode", "목록");
        mv.addObject("page", "bbs");

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping("view")
    public ModelAndView BBSDataView(@RequestParam(required = false) String code
            , @RequestParam(required = false) long idx) {
        adminBbsDataService.updateBBSDataReadCnt(idx);
        ModelAndView mv = new ModelAndView();
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        mv.addObject("mst_bbs", bbsManageVO);
        mv.addObject("menuList", adminMenuService.selectAdminMenu());
        mv.addObject("bbs_mode", "상세보기");
        mv.addObject("bbsObj", adminBbsDataService.getBBSData(idx));
        mv.addObject("bbs_template", "bbs_view");
        mv.addObject("page", "bbs");
        mv.setViewName("cms/template");
        return mv;
    }


    @RequestMapping("write")
    public ModelAndView BBSDataWrite(@RequestParam(required = false) String code) {
        ModelAndView mv = new ModelAndView();
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        mv.addObject("mst_bbs", bbsManageVO);
        mv.addObject("menuList", adminMenuService.selectAdminMenu());
        mv.addObject("bbs_mode", "등록");
        mv.addObject("action", "write.do");
        mv.addObject("bbs_template", "bbs_write");
        mv.addObject("page", "bbs");
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = {"write.do"}, method = RequestMethod.POST)
    public String BBSDataWriteProcess(@Valid BBSDataVO bbsVO, BindingResult bindingResult) {
        System.out.println(bbsVO);
        if(bindingResult.getFieldError("hid_file") == null){
            bbsVO.setHid_file("NULL");
        }
        if(bindingResult.getFieldError("bbs_file0") == null){
            bbsVO.setBbs_file0("NULL");
        }
        if(bindingResult.getFieldError("bbs_file1") == null){
            bbsVO.setBbs_file1("NULL");
        }
        if(bindingResult.getFieldError("bbs_file2") == null){
            bbsVO.setBbs_file2("NULL");
        }
        adminBbsDataService.insertBBSData(bbsVO);

        return "redirect:list?code=" + bbsVO.getCode();
    }

    @RequestMapping("edit")
    public ModelAndView BBSDataEdit(@RequestParam(required = false) String code
            , @RequestParam(required = false) long idx) {
        ModelAndView mv = new ModelAndView();
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        mv.addObject("mst_bbs", bbsManageVO);
        mv.addObject("menuList", adminMenuService.selectAdminMenu());
        mv.addObject("bbsObj", adminBbsDataService.getBBSData(idx));
        mv.addObject("action", "edit.do");
        mv.addObject("bbs_mode", "수정");
        mv.addObject("bbs_template", "bbs_write");
        mv.addObject("page", "bbs");
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = {"edit.do"}, method = RequestMethod.POST)
    public String BBSDataEditProcess(BBSDataVO bbsVO) {
        adminBbsDataService.updateBBSData(bbsVO);
        return "redirect:list?code=" + bbsVO.getCode();
    }

    @RequestMapping("delete.do")
    public String BBSDataDeleteProcess(@RequestParam(required = false) String code
            , @RequestParam(required = false) long idx) {
        adminBbsDataService.deleteBBSData(idx);
        return "redirect:list?code=" + code;
    }

}
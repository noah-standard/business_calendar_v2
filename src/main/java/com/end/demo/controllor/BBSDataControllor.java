package com.end.demo.controllor;

import com.end.demo.lib.Pager;
import com.end.demo.service.BBSDataService;
import com.end.demo.service.cms.AdminBBSDataService;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.BBSManageService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.BBSManageVO;
import com.end.demo.vo.BBSDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bbs")
public class BBSDataControllor {

    @Autowired
    AdminBBSDataService adminBbsDataService;

    @Autowired
    BBSDataService bbsDataService;

    @Autowired
    PageManageService pageManageService;

    @Autowired
    AdminMenuService adminMenuService;

    @Autowired
    BBSManageService bbsManageService;

    @RequestMapping("")
    public ModelAndView BBSDataList(@RequestParam(required = false) String code
            , @RequestParam(required = false) String mode
            , @RequestParam(defaultValue = "1") int curPage
            , @RequestParam(defaultValue = "all") String search_order
            , @RequestParam(defaultValue = "") String keyword
            , @RequestParam(required = false) String idx
            , @RequestParam(defaultValue = "0") int list_scale
            , @RequestParam(defaultValue = "0") int list_order) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("menuList", adminMenuService.selectAdminMenu());

        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        // 레코드 갯수 계산
        int count = adminBbsDataService.countBBSData(search_order, keyword, code);
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = Integer.parseInt(bbsManageVO.getList_page());
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

        mv.addObject("mst_bbs", bbsManageVO);
        mv.addObject("bbsList", bbsListVO);
        mv.addObject("bbs_count", count);
        mv.addObject("search_order", search_order);
        mv.addObject("keyword", keyword);
        mv.addObject("pager", pager);
        mv.addObject("userMenuList",pageManageService.selectUserMenu());
        mv.addObject("bbs_template", "skin/bbs_list");
        mv.addObject("page", "bbs/bbs");

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/view")
    public ModelAndView BBSDataView(@RequestParam(required = false) String code,@RequestParam(required = false) int idx) {
        adminBbsDataService.updateBBSDataReadCnt(idx);
        ModelAndView mv = new ModelAndView();

        mv.addObject("BBSObject", bbsDataService.getBBSData(idx));
        mv.addObject("mode", "상세보기");
        mv.addObject("userMenuList",pageManageService.selectUserMenu());
        mv.addObject("bbs_template", "skin/bbs_view");
        mv.addObject("page", "bbs/bbs");
        mv.setViewName("index");
        return mv;
    }

}

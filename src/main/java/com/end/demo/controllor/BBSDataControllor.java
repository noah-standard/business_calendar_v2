package com.end.demo.controllor;

import com.end.demo.lib.Pager;
import com.end.demo.service.BBSDataService;
import com.end.demo.service.cms.AdminBBSDataService;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.BBSManageService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.BBSManageVO;
import com.end.demo.vo.BBSDataVO;
import com.end.demo.vo.param.BBSPagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ModelAttribute
    public void template(Model model){
        model.addAttribute("page", "bbs/bbs");
        model.addAttribute("userMenuList",pageManageService.selectUserMenu());
    }

    @GetMapping("list")
    public String BBSDataList(BBSPagerVO bbsPagerVO, BindingResult bindingResult, Model model) {

        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(bbsPagerVO.getCode());

        // 레코드 갯수 계산
        int count = adminBbsDataService.countBBSData(bbsPagerVO.getSearch_order(), bbsPagerVO.getKeyword(), bbsPagerVO.getCode());
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = bbsManageVO.getList_page();
        int listCnt = 0;
        int curPage = bbsPagerVO.getCurPage();
        if (bbsPagerVO.getList_scale() == 0) {
            listCnt = bbsManageVO.getList_height();
        } else {
            listCnt = bbsPagerVO.getList_scale();
        }

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, curPage, listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();
        List<BBSDataVO> bbsListVO = adminBbsDataService.selectBBSData(start, end, bbsPagerVO.getSearch_order(), bbsPagerVO.getKeyword(), bbsPagerVO.getCode(), bbsPagerVO.getList_order());

        model.addAttribute("mst_bbs", bbsManageVO);
        model.addAttribute("bbsList", bbsListVO);
        model.addAttribute("bbs_count", count);
        model.addAttribute("search_order", bbsPagerVO.getSearch_order());
        model.addAttribute("keyword", bbsPagerVO.getKeyword());
        model.addAttribute("pager", pager);
        model.addAttribute("bbs_template", "skin/bbs_list");
        return "index";
    }

    @GetMapping("/view")
    public String BBSDataView(@RequestParam(required = false) int idx,Model model) {
        adminBbsDataService.updateBBSDataReadCnt(idx); // 조회수 증가
        model.addAttribute("BBSObject", bbsDataService.getBBSData(idx));
        model.addAttribute("bbs_template", "skin/bbs_view");
        return "index";
    }

}

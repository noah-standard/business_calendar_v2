package com.end.demo.controllor.cms;


import com.end.demo.lib.Pager;
import com.end.demo.service.cms.AdminBBSDataService;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.BBSManageService;
import com.end.demo.vo.BBSDataVO;
import com.end.demo.vo.BBSManageVO;
import com.end.demo.vo.param.BBSPagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute
    public void pageInfo(Model model){
        model.addAttribute("menuList", adminMenuService.selectAdminMasterMenu());
        model.addAttribute("page", "bbs");
    }

    // 특정 모델 객체에만 적용을 하고 싶을 경우 아래와 같이 이름을 지정.
//    @InitBinder("BBSDataVO")
//    public void initEventBinder(WebDataBinder webDataBinder) {
//        webDataBinder.setDisallowedFields("idx");
//    }

    @GetMapping("list")
    public String BBSDataList(@ModelAttribute BBSPagerVO bbsPagerVO, BindingResult bindingResult, Model model) {
        String search_order = bbsPagerVO.getSearch_order();
        String keyword = bbsPagerVO.getKeyword();
        String code = bbsPagerVO.getCode();
        int list_order = bbsPagerVO.getList_order();
        int listCnt = bbsPagerVO.getList_scale();
        int curPage = bbsPagerVO.getCurPage();

        System.out.println("=================");
        System.out.println(bbsPagerVO);
        System.out.println("=================");
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);

        if(listCnt == 0){
            listCnt = bbsManageVO.getList_height();
        }
        // 레코드 갯수 계산
        int count = adminBbsDataService.countBBSData(search_order, keyword, code);
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = bbsManageVO.getList_page();

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, curPage, listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();
        List<BBSDataVO> bbsListVO = adminBbsDataService.selectBBSData(start, end, search_order, keyword, code, list_order);
        System.out.println("================");
        System.out.println(bbsListVO);
        System.out.println("================");

        model.addAttribute("mst_bbs", bbsManageVO);
        model.addAttribute("bbsList", bbsListVO);
        model.addAttribute("bbs_count", count);
        model.addAttribute("search_order", search_order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pager", pager);
        model.addAttribute("bbs_template", "bbs_list");
        model.addAttribute("bbs_mode", "목록");

        return "cms/template";
    }

    @GetMapping("view")
    public String BBSDataView(@RequestParam(required = false) int idx,
                              @RequestParam(required = false) String code,
                              Model model) {

        adminBbsDataService.updateBBSDataReadCnt(idx);
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        model.addAttribute("mst_bbs", bbsManageVO);
        model.addAttribute("bbs_mode", "상세보기");
        model.addAttribute("bbsObj", adminBbsDataService.getBBSData(idx));
        model.addAttribute("bbs_template", "bbs_view");

        return "cms/template";
    }


    @GetMapping("write")
    public String BBSDataWrite(@RequestParam(required = false) String code, Model model) {
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        model.addAttribute("mst_bbs", bbsManageVO);
        model.addAttribute("bbs_mode", "등록");
        model.addAttribute("action", "write.do");
        model.addAttribute("bbs_template", "bbs_write");
        return "cms/template";
    }

    @PostMapping("write.do")
    public String BBSDataWriteProcess(@Valid @ModelAttribute BBSDataVO bbsVO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println("===============");
            System.out.println(bbsVO);
            System.out.println(bindingResult);
            System.out.println("===============");
            return "redirect:write";
        }
        adminBbsDataService.insertBBSData(bbsVO);
        return "redirect:list?code=" + bbsVO.getCode();
    }

    @GetMapping("edit")
    public String BBSDataEdit(@RequestParam(required = false) int idx,
                              @RequestParam(required = false) String code,
                              Model model) {
        BBSManageVO bbsManageVO = bbsManageService.getBBSManageCode(code);
        model.addAttribute("mst_bbs", bbsManageVO);
        model.addAttribute("bbsObj", adminBbsDataService.getBBSData(idx));
        model.addAttribute("action", "edit.do");
        model.addAttribute("bbs_mode", "수정");
        model.addAttribute("bbs_template", "bbs_write");
        return "cms/template";
    }

    @PostMapping("edit.do")
    public String BBSDataEditProcess(@Valid @ModelAttribute BBSDataVO bbsVO,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect:edit";
        }
        adminBbsDataService.updateBBSData(bbsVO);
        return "redirect:list?code=" + bbsVO.getCode();
    }

    @GetMapping("delete.do")
    public String BBSDataDeleteProcess(@RequestParam(required = false) String code,
                                       @RequestParam(required = false) long idx) {
        adminBbsDataService.deleteBBSData(idx);
        return "redirect:list?code=" + code;
    }

}
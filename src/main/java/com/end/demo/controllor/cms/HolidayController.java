package com.end.demo.controllor.cms;

import com.end.demo.lib.Util;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.HolidayService;
import com.end.demo.lib.Pager;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.param.BBSPagerVO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cms/holiday")
public class HolidayController {

    @Autowired
    HolidayService holidayService;

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMenuService adminMenuService;

    @ModelAttribute
    public void pageInfo(Model model){
        model.addAttribute("page", "holiday");
        model.addAttribute("menuList",adminMenuService.selectAdminMasterMenu());
    }

    @GetMapping("/list")
    public String holidayList(@ModelAttribute BBSPagerVO bbsPagerVO, Model model) {
        String search_order = bbsPagerVO.getSearch_order();
        String keyword = bbsPagerVO.getKeyword();
        int curPage = bbsPagerVO.getCurPage();
        int list_order = bbsPagerVO.getList_order();
        // 레코드 갯수 계산
        int count = holidayService.countHolidayData(search_order, keyword);
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = 10;
        int listCnt = 10;

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, curPage, listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();
        List<HolidayVO> holidayList = holidayService.selectHolidayList(start, end, search_order, keyword, list_order);
        model.addAttribute("holidayList", holidayList);
        model.addAttribute("listCount", count);
        model.addAttribute("search_order", search_order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pager", pager);
        model.addAttribute("holiday_template","holiday_list");
        model.addAttribute("mode", "목록");

        return "cms/template";
    }

    @GetMapping("/write")
    public String holidayWrite(Model model) {
        model.addAttribute("holiday_template","holiday_write");
        model.addAttribute("mode", "등록");
        return "cms/template";
    }

    @PostMapping("/write.do")
    public String holidayWriteProcess(@Valid @ModelAttribute HolidayVO holidayVO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect:write";
        }
        holidayService.writeHoliday(holidayVO);
        return "redirect:list";
    }

    @GetMapping("/edit")
    public String holidayEdit(@RequestParam int idx,Model model) {
        model.addAttribute("holiday_template","holiday_write");
        model.addAttribute("mode", "수정");
        model.addAttribute("holidayObject",holidayService.getHoliday(idx));
        return "cms/template";
    }

    @PostMapping("/edit.do")
    public String holidayEditProcess(@Valid @ModelAttribute HolidayVO holidayVO,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "redirect:edit";
        }
        holidayService.editHoliday(holidayVO);
        return "redirect:list";
    }

    @GetMapping("/delete.do")
    public String holidayDelete(@RequestParam int idx) {
        holidayService.deleteHoliday(idx);
        return "redirect:list";
    }

}
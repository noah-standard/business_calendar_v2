package com.end.demo.controllor.cms;

import com.end.demo.lib.Util;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.HolidayService;
import com.end.demo.lib.Pager;
import com.end.demo.vo.HolidayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/list.do")
    public ModelAndView holidayList(Model model) {
        Map<String, Object> map = model.asMap();
        ModelAndView mv = new ModelAndView();

        String search_order = (String) map.get("search_order");
        String keyword = (String) map.get("keyword");
        int curPage = 0;
        if(Util.isEmpty(map.get("curPage"))) {
            curPage = 0;
        }else{
            curPage = (int)map.get("curPage");
        }
        int list_order = 0;
        if(Util.isEmpty(map.get("list_order"))){
            list_order = 10;
        }else{
            list_order = (int)map.get("list_order");
        }

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
        mv.addObject("holidayList", holidayList);
        mv.addObject("listCount", count);
        mv.addObject("search_order", search_order);
        mv.addObject("keyword", keyword);
        mv.addObject("pager", pager);
        mv.addObject("page", "holiday");
        mv.addObject("holiday_template","holiday_list");
        mv.addObject("mode", "목록");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping("/write.do")
    public ModelAndView holidayWrite() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("page", "holiday");
        mv.addObject("holiday_template","holiday_write");
        mv.addObject("mode", "등록");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = "/write.do",method = RequestMethod.POST)
    public String holidayWriteProcess(HolidayVO holidayVO) {
        holidayService.writeHoliday(holidayVO);
        return "redirect:list.do";
    }

    @RequestMapping("/edit.do")
    public ModelAndView holidayEdit(@RequestParam int idx) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("page", "holiday");
        mv.addObject("holiday_template","holiday_write");
        mv.addObject("mode", "수정");
        mv.addObject("holidayObject",holidayService.getHoliday(idx));

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = "/edit.do",method = RequestMethod.POST)
    public String holidayEditProcess(HolidayVO holidayVO) {
        holidayService.editHoliday(holidayVO);
        return "redirect:list.do";
    }

    @RequestMapping("/delete.do")
    public String holidayDelete(@RequestParam int idx) {
        holidayService.deleteHoliday(idx);
        return "redirect:list.do";
    }

}
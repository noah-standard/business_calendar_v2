package com.end.demo.controllor.cms;

import com.end.demo.lib.Pager;
import com.end.demo.lib.Util;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.CalendarService;
import com.end.demo.service.cms.MemberService;
import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.join.CalendarMemberVO;
import com.end.demo.vo.param.BBSPagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cms/calendar")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @Autowired
    MemberService memberService;

    @Autowired
    AdminMenuService adminMenuService;

    @ModelAttribute
    public void pageInfo(Model model){
        model.addAttribute("page", "calendar");
        model.addAttribute("menuList",adminMenuService.selectAdminMenu());
    }

    @GetMapping("/list")
    public String calendarList(@ModelAttribute BBSPagerVO bbsPagerVO, Model model) {
        String search_order = bbsPagerVO.getSearch_order();
        String keyword = bbsPagerVO.getKeyword();
        int curPage = bbsPagerVO.getCurPage();
        int list_order = bbsPagerVO.getList_order();

        // 레코드 갯수 계산
        int count = calendarService.countCalendarData(search_order, keyword);
        // 페이지블록 및 목록수
        int pageCnt = 10;
        int listCnt = 10;

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, curPage, listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();

        // 리스트 가져오기
        List<CalendarMemberVO> calendarList = calendarService.selectCalendarList(start, end, search_order, keyword, list_order);

        List<CalendarMemberVO> calendarMemberVOS = new ArrayList<>();

        for(CalendarMemberVO item :calendarList){
            MemberVO memberVO = memberService.getMember(item.getMember_idx());
            CalendarMemberVO calendarMemberVO = new CalendarMemberVO();
            calendarMemberVO.setUserid(memberVO.getUserid());
            calendarMemberVO.setRn(item.getRn());
            calendarMemberVO.setIdx(item.getIdx());
            calendarMemberVO.setContent(item.getContent());
            calendarMemberVO.setS_date(item.getS_date());
            calendarMemberVO.setE_date(item.getE_date());
            calendarMemberVO.setName(memberVO.getName());
            calendarMemberVO.setType(item.getType());
            calendarMemberVO.setState(item.getState());
            calendarMemberVO.setRn(item.getRn());
            calendarMemberVO.setReg_date(item.getReg_date());

            calendarMemberVOS.add(calendarMemberVO);
        }

        // 보낼 객체
        model.addAttribute("calendarMemberList", calendarMemberVOS);
        model.addAttribute("listCount", count);
        model.addAttribute("search_order", search_order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pager", pager);
        model.addAttribute("calendar_template","calendar_list");
        model.addAttribute("mode", "목록");

        return "cms/template";
    }

    @GetMapping("/write")
    public String calendarWrite(Model model) {
        model.addAttribute("calendar_template","calendar_write");
        model.addAttribute("action","write.do");
        model.addAttribute("mode", "등록");
        return "cms/template";
    }

    @PostMapping("/write.do")
    public String calendarWriteProcess(@ModelAttribute CalendarMemberVO calendarMemberVO, BindingResult bindingResult) {
        calendarService.writeCalendar(calendarMemberVO);
        return "redirect:list";
    }

    @GetMapping("/edit")
    public String calendarEdit(@RequestParam int idx,Model model) {
        model.addAttribute("calendar_template","calendar_write");
        model.addAttribute("action","edit.do");
        model.addAttribute("mode", "수정");
        model.addAttribute("calendarObject",calendarService.getCalendar(idx));
        return "cms/template";
    }

    @PostMapping("/edit.do")
    public String calendarEditProcess(@ModelAttribute CalendarMemberVO calendarMemberVO) {
        calendarService.editCalendar(calendarMemberVO);
        return "redirect:list";
    }

    @GetMapping("/delete.do")
    public String calendarDelete(@RequestParam int idx) {
        calendarService.deleteCalendar(idx);
        return "redirect:list";
    }

    @GetMapping("/view")
    public String calendarView(@RequestParam int idx,Model model) {
        model.addAttribute("calendarObject",calendarService.getCalendar(idx));
        model.addAttribute("calendar_template","calendar_view");
        model.addAttribute("mode", "상세보기");
        return "cms/template";
    }

}
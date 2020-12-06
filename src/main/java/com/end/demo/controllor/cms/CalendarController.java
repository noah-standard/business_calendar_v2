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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/list.do")
    public ModelAndView calendarList(@RequestParam(defaultValue = "1") int curPage
                                ,@RequestParam(defaultValue = "all") String search_order
                                ,@RequestParam(defaultValue = "") String keyword
                                ,@RequestParam(required = false) String idx
                                ,@RequestParam(defaultValue = "0") int list_scale
                                ,@RequestParam(defaultValue = "0") int list_order) {

        ModelAndView mv = new ModelAndView();

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
        mv.addObject("calendarMemberList", calendarMemberVOS);
        mv.addObject("listCount", count);
        mv.addObject("search_order", search_order);
        mv.addObject("keyword", keyword);
        mv.addObject("pager", pager);
        mv.addObject("page", "calendar");
        mv.addObject("calendar_template","calendar_list");
        mv.addObject("mode", "목록");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping("/write.do")
    public ModelAndView calendarWrite() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("page", "calendar");
        mv.addObject("calendar_template","calendar_write");
        mv.addObject("mode", "등록");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = "/write.do",method = RequestMethod.POST)
    public String calendarWriteProcess(CalendarMemberVO calendarMemberVO) {
        calendarService.writeCalendar(calendarMemberVO);
        return "redirect:list.do";
    }

    @RequestMapping("/edit.do")
    public ModelAndView calendarEdit(@RequestParam int idx) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("page", "calendar");
        mv.addObject("calendar_template","calendar_write");
        mv.addObject("mode", "수정");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());
        mv.addObject("calendarObject",calendarService.getCalendar(idx));

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = "/edit.do",method = RequestMethod.POST)
    public String calendarEditProcess(CalendarMemberVO calendarMemberVO) {
        calendarService.editCalendar(calendarMemberVO);
        return "redirect:list.do";
    }

    @RequestMapping("/delete.do")
    public String calendarDelete(@RequestParam int idx) {
        HashMap<String, Object> delData = new HashMap<>();
        delData.put("idx",idx);
        calendarService.deleteCalendar(delData);
        return "redirect:list.do";
    }

    @RequestMapping("/view.do")
    public ModelAndView calendarView(@RequestParam int idx) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("calendarObject",calendarService.getCalendar(idx));
        mv.addObject("page", "calendar");
        mv.addObject("calendar_template","calendar_view");
        mv.addObject("mode", "상세보기");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

}
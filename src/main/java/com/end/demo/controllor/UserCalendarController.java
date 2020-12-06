package com.end.demo.controllor;

import com.end.demo.lib.Pager;
import com.end.demo.service.UserService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.CalendarService;
import com.end.demo.service.cms.MemberService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.join.CalendarMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class UserCalendarController {

    @Autowired
    CalendarService calendarService;

    @Autowired
    MemberService memberService;

    @Autowired
    AdminService adminService;

    @Autowired
    PageManageService pageManageService;

    @Autowired
    UserService userService;

    @RequestMapping("/list")
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
        mv.addObject("page", "bbs/template_mypage");
        mv.addObject("mypage_template","skin/calendar_list");
        mv.addObject("mode", "목록");
        mv.addObject("userMenuList",pageManageService.selectUserMenu());

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/write")
    public ModelAndView calendarWrite( HttpSession session) {
        ModelAndView mv = new ModelAndView();

        String userid = (String) session.getAttribute("userid");
        UserVO userVO = userService.getUser(userid);

        mv.addObject("userMenuList",pageManageService.selectUserMenu());
        mv.addObject("memberObject",memberService.getMember(userVO.getIdx()));
        mv.addObject("page", "bbs/template_calendar");
        mv.addObject("calendar_template","skin/calendar_write");
        mv.addObject("mode", "등록");

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/write.do",method = RequestMethod.POST)
    public String calendarWriteProcess(CalendarMemberVO calendarMemberVO) {
        calendarService.writeCalendar(calendarMemberVO);
        return "redirect:/";
    }


}
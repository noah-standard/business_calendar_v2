package com.end.demo.controllor;

import com.end.demo.lib.Pager;
import com.end.demo.service.UserService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.CalendarService;
import com.end.demo.service.cms.MemberService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.param.BBSPagerVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.join.CalendarMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public String calendarList(@ModelAttribute BBSPagerVO bbsPagerVO, Model model) {

        // 레코드 갯수 계산
        int count = calendarService.countCalendarData(bbsPagerVO.getSearch_order(), bbsPagerVO.getKeyword());
        // 페이지블록 및 목록수
        int pageCnt = 10;
        int listCnt = 10;

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, bbsPagerVO.getCurPage(), listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();

        // 리스트 가져오기
        List<CalendarMemberVO> calendarList = calendarService.selectCalendarList(start, end, bbsPagerVO.getSearch_order(), bbsPagerVO.getKeyword(), bbsPagerVO.getList_order());

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
        model.addAttribute("search_order", bbsPagerVO.getSearch_order());
        model.addAttribute("keyword", bbsPagerVO.getKeyword());
        model.addAttribute("pager", pager);
        model.addAttribute("page", "bbs/template_mypage");
        model.addAttribute("mypage_template","skin/calendar_list");
        model.addAttribute("mode", "목록");
        model.addAttribute("userMenuList",pageManageService.selectUserMenu());
        return "index";
    }

    @GetMapping("/write")
    public String calendarWrite(HttpSession session,Model model) {
        String userid = (String) session.getAttribute("userid");
        UserVO userVO = userService.getUser(userid);

        model.addAttribute("userMenuList",pageManageService.selectUserMenu());
        model.addAttribute("memberObject",memberService.getMember(userVO.getIdx()));
        model.addAttribute("page", "bbs/template_calendar");
        model.addAttribute("calendar_template","skin/calendar_write");
        model.addAttribute("mode", "등록");
        return "index";
    }

    @PostMapping("/write.do")
    public String calendarWriteProcess(CalendarMemberVO calendarMemberVO) {
        calendarService.writeCalendar(calendarMemberVO);
        return "redirect:/";
    }


}
package com.end.demo.controllor;

import com.end.demo.service.UserService;
import com.end.demo.service.cms.*;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.join.CalendarMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    UserService userService;

    @Autowired
    PageManageService pageManageService;

    @Autowired
    MemberService memberService;

    @Autowired
    VacationService vacationService;

    @ModelAttribute
    public void memberInfo(HttpSession session,Model model){
        String userid = (String)session.getAttribute("userid");
        UserVO userVO = userService.getUser(userid);
        MemberVO memberVO = memberService.getMember(userVO.getIdx());

        model.addAttribute("getVacation",vacationService.getVacation(userVO.getIdx()));
        model.addAttribute("getVacationApply",vacationService.getVacationApply(userVO.getIdx()));
        model.addAttribute("userMenuList",pageManageService.selectUserMenu());
        model.addAttribute("memberObject",memberVO);
        model.addAttribute("memberLevelObject",memberService.getMemberLevel(memberVO.getMem_level()));
        model.addAttribute("page", "bbs/template_mypage");
    }

    @GetMapping("")
    public String memberView(Model model) {
        model.addAttribute("mypage_template","skin/mypage_view");
        return "index";
    }

    @GetMapping("/edit")
    public String memberEdit(Model model) {
        model.addAttribute("mypage_template","skin/mypage_write");
        model.addAttribute("mode", "정보수정");
        return "index";
    }

    @PostMapping("/edit.do")
    public String calendarWriteProcess(MemberVO memberVO) {
        memberService.editMember(memberVO);
        return "redirect:/mypage";
    }

}
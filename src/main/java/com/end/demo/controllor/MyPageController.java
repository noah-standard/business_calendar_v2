package com.end.demo.controllor;

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

    @ModelAttribute
    public void memberInfo(WebSession session,Model model){
        String userid = session.getAttribute("userid");
        UserVO userVO = userService.getUser(userid);
        MemberVO memberVO = memberService.getMember(userVO.getIdx());

        model.addAttribute("userMenuList",pageManageService.selectUserMenu());
        model.addAttribute("memberObject",memberVO);
        model.addAttribute("memberLevelObject",memberService.getMemberLevel(memberVO.getMem_level()));
        model.addAttribute("page", "bbs/template_mypage");
    }

    @GetMapping("")
    public String memberVie(Model model) {
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
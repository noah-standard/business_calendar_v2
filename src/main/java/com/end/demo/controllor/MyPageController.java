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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping("")
    public ModelAndView memberView(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        String userid = (String) session.getAttribute("userid");
        UserVO userVO = userService.getUser(userid);

        MemberVO memberVO = memberService.getMember(userVO.getIdx());
        mv.addObject("userMenuList",pageManageService.selectUserMenu());
        mv.addObject("memberObject",memberVO);
        mv.addObject("memberLevelObject",memberService.getMemberLevel(memberVO.getMem_level()));
        mv.addObject("page", "bbs/template_mypage");
        mv.addObject("mypage_template","skin/mypage_view");
//        mv.addObject("mode", "정보보기");

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView memberEdit(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        String userid = (String) session.getAttribute("userid");
        UserVO userVO = userService.getUser(userid);
        MemberVO memberVO = memberService.getMember(userVO.getIdx());

        mv.addObject("userMenuList",pageManageService.selectUserMenu());
        mv.addObject("memberObject",memberVO);
        mv.addObject("memberLevelObject",memberService.getMemberLevel(memberVO.getMem_level()));
        mv.addObject("page", "bbs/template_mypage");
        mv.addObject("mypage_template","skin/mypage_write");
        mv.addObject("mode", "정보수정");

        mv.setViewName("index");
        return mv;
    }


    @RequestMapping(value = "/edit.do",method = RequestMethod.POST)
    public String calendarWriteProcess(MemberVO memberVO) {
        memberService.editMember(memberVO);
        return "redirect:/mypage";
    }

}
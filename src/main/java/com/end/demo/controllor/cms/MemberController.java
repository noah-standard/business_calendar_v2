package com.end.demo.controllor.cms;

import com.end.demo.lib.Pager;
import com.end.demo.lib.Util;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.MemberService;
import com.end.demo.vo.MemLevelVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.join.MemberMemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cms/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    AdminMenuService adminMenuService;

    @RequestMapping("/list.do")
    public ModelAndView memberList(@RequestParam(defaultValue = "1") int curPage
                                ,@RequestParam(defaultValue = "all") String search_order
                                ,@RequestParam(defaultValue = "") String keyword
                                ,@RequestParam(required = false) String idx
                                ,@RequestParam(defaultValue = "0") int list_scale
                                ,@RequestParam(defaultValue = "0") int list_order) {

        ModelAndView mv = new ModelAndView();

        // 레코드 갯수 계산
        int count = memberService.countMemberData(search_order, keyword);
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = 10;
        int listCnt = 10;

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, curPage, listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();

        // 리스트 가져오기
        List<MemberVO> memberList = memberService.selectMemberList(start, end, search_order, keyword, list_order);

        // member | mem_level
        List<MemberMemVO> memberMemVOS = new ArrayList<>();

        for(MemberVO memberVO : memberList){
            MemberMemVO memberMemVO = new MemberMemVO();
            MemLevelVO memLevelVO = memberService.getMemberLevel(memberVO.getMem_level());
            memberMemVO.setRn(memberVO.getRn());
            memberMemVO.setIdx(memberVO.getIdx());
            memberMemVO.setUserid(memberVO.getUserid());
            memberMemVO.setName(memberVO.getName());
            memberMemVO.setZip(memberVO.getZip());
            memberMemVO.setAddr1(memberVO.getAddr1());
            memberMemVO.setAddr2(memberVO.getAddr2());
            memberMemVO.setIdx(memberVO.getIdx());
            memberMemVO.setMem_level(memberVO.getMem_level());
            memberMemVO.setPhone1(memberVO.getPhone1());
            memberMemVO.setPhone2(memberVO.getPhone2());
            memberMemVO.setReg_date(memberVO.getReg_date());
            memberMemVO.setMem_name(memLevelVO.getMem_name());

            memberMemVOS.add(memberMemVO);
        }

        // 보낼 객체
        mv.addObject("memberList", memberMemVOS);
        mv.addObject("listCount", count);
        mv.addObject("search_order", search_order);
        mv.addObject("keyword", keyword);
        mv.addObject("pager", pager);
        mv.addObject("page", "member");
        mv.addObject("member_template","member_list");
        mv.addObject("mode", "목록");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping("/write.do")
    public ModelAndView memberWrite() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("memberLevelList",memberService.selectMemberLevelList());
        mv.addObject("page", "member");
        mv.addObject("member_template","member_write");
        mv.addObject("mode", "등록");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = "/write.do",method = RequestMethod.POST)
    public String memberWriteProcess(MemberVO memberVO) {
        memberService.writeMember(memberVO);
        return "redirect:list.do";
    }

    @RequestMapping("/edit.do")
    public ModelAndView memberEdit(@RequestParam int idx) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("memberLevelList",memberService.selectMemberLevelList());
        mv.addObject("page", "member");
        mv.addObject("member_template","member_write");
        mv.addObject("mode", "수정");
        mv.addObject("menuList",adminMenuService.selectAdminMenu());
        mv.addObject("memberObject",memberService.getMember(idx));

        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value = "/edit.do",method = RequestMethod.POST)
    public String memberEditProcess(MemberVO memberVO) {
        memberService.editMember(memberVO);
        return "redirect:list.do";
    }

    @RequestMapping("/delete.do")
    public String memberDelete(@RequestParam int idx) {
        HashMap<String, Object> delData = new HashMap<>();
        delData.put("idx",idx);
        delData.put("del_date",Util.getDate("timestamp"));
        memberService.deleteMember(delData);
        return "redirect:list.do";
    }

}
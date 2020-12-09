package com.end.demo.controllor.cms;

import com.end.demo.lib.Pager;
import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.MemberService;
import com.end.demo.vo.param.BBSPagerVO;
import com.end.demo.vo.MemLevelVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.join.MemberMemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @ModelAttribute
    public void pageInfo(Model model){
        model.addAttribute("page", "member");
        model.addAttribute("menuList",adminMenuService.selectAdminMenu());
    }

    @GetMapping("/list")
    public String memberList(@ModelAttribute BBSPagerVO bbsPagerVO, Model model) {
        // 레코드 갯수 계산
        int count = memberService.countMemberData(bbsPagerVO.getSearch_order(), bbsPagerVO.getKeyword());
        // mst_bbs 페이지블록 및 목록수
        int pageCnt = 10;
        int listCnt = 10;

        // 페이지 나누기 관련처리
        Pager pager = new Pager(count, bbsPagerVO.getCurPage(),  listCnt, pageCnt);
        int start = pager.getPageBegin();
        int end = pager.getPageEnd();

        // 리스트 가져오기
        List<MemberVO> memberList = memberService.selectMemberList(start, end, bbsPagerVO.getSearch_order(), bbsPagerVO.getKeyword(), bbsPagerVO.getList_order());

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
        model.addAttribute("memberList", memberMemVOS);
        model.addAttribute("listCount", count);
        model.addAttribute("search_order", bbsPagerVO.getSearch_order());
        model.addAttribute("keyword", bbsPagerVO.getKeyword());
        model.addAttribute("pager", pager);
        model.addAttribute("member_template","member_list");
        model.addAttribute("mode", "목록");

        return "cms/template";
    }

    @GetMapping("/write")
    public String memberWrite(Model model) {
        model.addAttribute("memberLevelList",memberService.selectMemberLevelList());
        model.addAttribute("action","write.do");
        model.addAttribute("member_template","member_write");
        model.addAttribute("mode", "등록");
        return "cms/template";
    }

    @PostMapping("/write.do")
    public String memberWriteProcess(@ModelAttribute MemberVO memberVO) {
        memberService.writeMember(memberVO);
        return "redirect:list";
    }

    @GetMapping("/edit")
    public String memberEdit(@RequestParam int idx,Model model) {
        model.addAttribute("memberLevelList",memberService.selectMemberLevelList());
        model.addAttribute("member_template","member_write");
        model.addAttribute("action","write.do");
        model.addAttribute("mode", "수정");
        model.addAttribute("memberObject",memberService.getMember(idx));
        return "cms/template";
    }

    @PostMapping("/edit.do")
    public String memberEditProcess(@ModelAttribute MemberVO memberVO) {
        memberService.editMember(memberVO);
        return "redirect:list.do";
    }

    @GetMapping("/delete.do")
    public String memberDelete(@RequestParam int idx) {
        HashMap<String, Object> delData = new HashMap<>();
        delData.put("idx",idx);
        delData.put("del_date", LocalDateTime.now());
        memberService.deleteMember(delData);
        return "redirect:list.do";
    }

}
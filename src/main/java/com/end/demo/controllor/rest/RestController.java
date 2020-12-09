package com.end.demo.controllor.rest;

import com.end.demo.api.XmlParsing;
import com.end.demo.lib.Pager;
import com.end.demo.service.UserService;
import com.end.demo.service.cms.CalendarService;
import com.end.demo.service.cms.MemberService;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.MemLevelVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.join.MemberMemVO;
import com.end.demo.vo.param.BBSPagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserService service;

    @Autowired
    MemberService memberService;

    @Autowired
    CalendarService calendarService;

    @GetMapping("/holiday")
    public String insertHoliday(HolidayVO hVo, @RequestParam String year) {
        if(!year.equals("")) {
            service.insertHoliday(XmlParsing.getData(year));
        }
        return "/";
    }

    @GetMapping("/cms/ajax/member")
    public String memberChkAjax(@RequestParam String userid){
        int memberChkStr = memberService.memberCheck(userid);
        String chkStr = "";
        if(memberChkStr > 0){
            chkStr = "no";
        }else{
            chkStr = "ok";
        }
        return chkStr;
    }

    @GetMapping("/cms/ajax/search")
    public ModelAndView memberSearchAjax(@ModelAttribute BBSPagerVO bbsPagerVO){
        String search_order = bbsPagerVO.getSearch_order();
        String keyword = bbsPagerVO.getKeyword();
        int curPage = bbsPagerVO.getCurPage();
        int list_order =bbsPagerVO.getList_order();

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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pager",pager);
        modelAndView.addObject("listCount", count);
        modelAndView.addObject("memberList",memberMemVOS);
        modelAndView.setViewName("/cms/calendar_write_member_search_ajax");
        return modelAndView;
    }


}

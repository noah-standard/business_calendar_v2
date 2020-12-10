package com.end.demo.service.cms;

import com.end.demo.dao.cms.IHolidayDAO;
import com.end.demo.dao.cms.IMemberDAO;
import com.end.demo.lib.Util;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.MemLevelVO;
import com.end.demo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Service
public class MemberService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    IMemberDAO dao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<MemberVO> selectMemberList(int start, int end, String search_order, String keyword, int list_order) {
        return dao.selectMemberList(start, end, search_order, keyword, list_order);
    }
    public List<MemLevelVO> selectMemLevel(){return dao.selectMemLevel();}
    public MemLevelVO getMemberLevel(String mem_level){return dao.getMemberLevel(mem_level);}

    public List<MemLevelVO> selectMemberLevelList(){return dao.selectMemberLevelList();}

    public int countMemberData(String search_order, String keyword) {
        return dao.countMemberData(search_order, keyword);
    }

    public MemberVO getMember(int idx){
        return dao.getMember(idx);
    }

    public void editMember(MemberVO memberVO){
        String password = passwordEncoder.encode(memberVO.getPassword());
        if(!memberVO.getPassword().equals("")) {
            memberVO.setPassword(password);
        }

        dao.editMember(memberVO);
    }

    public void deleteMember(HashMap<String, Object> delData){
        dao.deleteMember(delData);
    }

    public int memberCheck(String userid){return dao.memberCheck(userid);}

    public void writeMember(MemberVO memberVO){
        memberVO.setDel_date(Util.getDate("timestamp"));
        memberVO.setReg_date(Util.getDate("timestamp"));
        memberVO.setReg_ip(Util.getIp());
        String password = passwordEncoder.encode(memberVO.getPassword());
        memberVO.setPassword(password);
        dao.writeMember(memberVO);
    }
}

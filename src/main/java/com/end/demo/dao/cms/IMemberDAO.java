package com.end.demo.dao.cms;


import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.MemLevelVO;
import com.end.demo.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IMemberDAO {
    public List<MemberVO> selectMemberList(int start, int end, String search_order, String keyword, int list_order);
    public List<MemLevelVO> selectMemLevel();
    public List<MemLevelVO> selectMemberLevelList();
    public MemLevelVO getMemberLevel(String mem_level);
    public int countMemberData(String search_order,String keyword);
    public void writeHoliday(HolidayVO holidayVO);
    public MemberVO getMember(int idx);
    public void editMember(MemberVO memberVO);
    public void deleteMember(HashMap<String, Object> delData);
    public int memberCheck(String userid);
    public void writeMember(MemberVO memberVO);
}
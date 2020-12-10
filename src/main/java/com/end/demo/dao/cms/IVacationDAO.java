package com.end.demo.dao.cms;


import com.end.demo.vo.join.CalendarMemberVO;
import com.end.demo.vo.join.VacationMemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IVacationDAO {
    public int getVacation(int idx);
    public double getVacationApply(int idx);
    public int getVacationTotal();
    public double getVacationApplyTotal();
    public List<VacationMemberVO> selectVacationList(int start, int end, String search_order, String keyword, int list_order);
}
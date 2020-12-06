package com.end.demo.dao.cms;


import com.end.demo.vo.HolidayVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IHolidayDAO {
    public List<HolidayVO> selectHolidayList(int start, int end, String search_order, String keyword,int list_order);
    public int countHolidayData(String search_order,String keyword);
    public void writeHoliday(HolidayVO holidayVO);
    public HolidayVO getHoliday(int idx);
    public void editHoliday(HolidayVO holidayVO);
    public void deleteHoliday(int idx);
}
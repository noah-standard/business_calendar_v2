package com.end.demo.dao.cms;


import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.join.CalendarMemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ICalendarDAO {
    public List<CalendarMemberVO> selectCalendarList(int start, int end, String search_order, String keyword, int list_order);
    public List<CalendarMemberVO> selectCalendarListNotparameter();
    public int countCalendarData(String search_order,String keyword);
    public CalendarMemberVO getCalendar(int idx);
    public void editCalendar(CalendarMemberVO calendarMemberVO);
    public void deleteCalendar(int idx);
    public void writeCalendar(CalendarMemberVO calendarMemberVO);
    public void updateState(HashMap<String,Integer> query);
}
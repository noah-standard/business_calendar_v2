package com.end.demo.service.cms;

import com.end.demo.dao.cms.ICalendarDAO;
import com.end.demo.dao.cms.IMemberDAO;
import com.end.demo.lib.Util;
import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.MemberVO;
import com.end.demo.vo.join.CalendarMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Service
public class CalendarService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    ICalendarDAO dao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<CalendarMemberVO> selectCalendarList(int start, int end, String search_order, String keyword, int list_order) {
        return dao.selectCalendarList(start, end, search_order, keyword, list_order);
    }

    public List<CalendarMemberVO> selectCalendarList() {
        return dao.selectCalendarListNotparameter();
    }

    public int countCalendarData(String search_order, String keyword) {
        return dao.countCalendarData(search_order, keyword);
    }

    public CalendarMemberVO getCalendar(int idx){
        return dao.getCalendar(idx);
    }

    public void editCalendar(CalendarMemberVO calendarMemberVO){
        calendarMemberVO.setE_date(calendarMemberVO.getS_date());
        dao.editCalendar(calendarMemberVO);
    }

    public int getVacationTotal(){return dao.getVacationTotal();}
    public int getVacationApplyTotal(){return dao.getVacationApplyTotal();}
    public int getVacationDisappear(){return dao.getVacationDisappear();}

    public void deleteCalendar(int idx){
        dao.deleteCalendar(idx);
    }

    public void writeCalendar(CalendarMemberVO calendarMemberVO){
        calendarMemberVO.setReg_date(Util.getDate("timestamp"));
        calendarMemberVO.setReg_ip(Util.getIp());
        calendarMemberVO.setE_date(calendarMemberVO.getS_date());
        dao.writeCalendar(calendarMemberVO);
    }

    public void updateState(HashMap<String,Integer> query){
        dao.updateState(query);
    }
}

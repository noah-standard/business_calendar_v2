package com.end.demo.service.cms;

import com.end.demo.dao.cms.IHolidayDAO;
import com.end.demo.lib.Util;
import com.end.demo.vo.HolidayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Service
public class HolidayService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    IHolidayDAO dao;

    public List<HolidayVO> selectHolidayList(int start, int end, String search_order, String keyword, int list_order) {
        return dao.selectHolidayList(start, end, search_order, keyword, list_order);
    }

    public int countHolidayData(String search_order, String keyword) {
        return dao.countHolidayData(search_order, keyword);
    }

    public void writeHoliday(HolidayVO holidayVO){
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
        Calendar time = Calendar.getInstance();
        String format_time1 = format1.format(time.getTime());
        holidayVO.setReg_ip(Util.getIp());
        holidayVO.setYear(format_time1);
        holidayVO.setReg_date(Util.getDate("timestamp"));
        dao.writeHoliday(holidayVO);
    }

    public HolidayVO getHoliday(int idx){
        return dao.getHoliday(idx);
    }

    public void editHoliday(HolidayVO holidayVO){
        dao.editHoliday(holidayVO);
    }

    public void deleteHoliday(int idx){
        dao.deleteHoliday(idx);
    }

}

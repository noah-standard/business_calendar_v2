package com.end.demo.dao.lib;


import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.XhrVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ICalDAO {
    public List<HolidayVO> getHoliday();

//    public void insertCalendar(CalendarVO cvo);

}
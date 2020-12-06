package com.end.demo.dao;


import com.end.demo.vo.CalendarVO;
import com.end.demo.vo.HolidayVO;
import com.end.demo.vo.UserVO;
import com.end.demo.vo.XhrVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IUserDAO {
    public List<CalendarVO> getCalendar(XhrVO values);

    public List<CalendarVO> getList(String name,
                                    @Param("sIndex")int sIndex,
                                    @Param("eIndex")int eIndex);

    public UserVO getUser(String userid);

    public int maxCountList(String name);

    public void insertCalendar(CalendarVO cvo);

    public void insertHoliday(HolidayVO hvo);

    public void updateDelete(List<Integer> i);

    public CalendarVO getDetail(int i);

    public void updateForm(CalendarVO cvo);

    public UserVO getEtc(String name);

    public void updateAgree(HashMap param);


}
package com.end.demo.service.cms;

import com.end.demo.dao.cms.ICalendarDAO;
import com.end.demo.dao.cms.IVacationDAO;
import com.end.demo.lib.Util;
import com.end.demo.vo.join.CalendarMemberVO;
import com.end.demo.vo.join.VacationMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;


@Service
public class VacationService {

    @Autowired
    IVacationDAO dao;

    public int getVacation(int idx){return dao.getVacation(idx);}
    public double getVacationApply(int idx){return dao.getVacationApply(idx);}
    public int getVacationTotal(){return dao.getVacationTotal();}
    public double getVacationApplyTotal(){return dao.getVacationApplyTotal();}
    public List<VacationMemberVO> selectVacationList(int start, int end, String search_order, String keyword, int list_order) {
        return dao.selectVacationList(start, end, search_order, keyword, list_order);
    }

}

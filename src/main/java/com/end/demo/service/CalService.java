package com.end.demo.service;

import com.end.demo.dao.lib.ICalDAO;
import com.end.demo.vo.HolidayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;


@Service
public class CalService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    ICalDAO dao;

    public List<HolidayVO> getHoliday() {
        return dao.getHoliday();
    }

}

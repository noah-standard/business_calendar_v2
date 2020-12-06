package com.end.demo.service.cms;

import com.end.demo.dao.cms.IAdminDAO;
import com.end.demo.vo.*;
import com.end.demo.vo.AdminMenuVO;
import com.end.demo.vo.AdminVO;
import com.end.demo.vo.BBSManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Service
public class AdminService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    IAdminDAO dao;



}

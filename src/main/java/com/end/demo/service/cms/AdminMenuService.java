package com.end.demo.service.cms;

import com.end.demo.dao.cms.IAdminDAO;
import com.end.demo.dao.cms.IAdminMenuDAO;
import com.end.demo.vo.*;
import com.end.demo.vo.param.PageManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Service
public class AdminMenuService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    IAdminMenuDAO dao;

    public void insertAdminMenu(PageManageVO pageManageVO){dao.insertAdminMenu(pageManageVO);}
    public List<AdminMenuVO> selectAdminMenu(){return dao.selectAdminMenu();}
    public List<AdminMenuVO> selectAdminMenuOne(String menu){return dao.selectAdminMenuOne(menu);}
    public AdminMenuVO selectOneAdminMenu(String node){return dao.selectOneAdminMenu(node);}
    public void updateAdminMenu(AdminMenuVO adminMenuVO){dao.updateAdminMenu(adminMenuVO);}
    public void updateAdminMenuOne(AdminMenuVO adminMenuVO){dao.updateAdminMenuOne(adminMenuVO);}
    public void deleteAdminMenuOne(String idx){dao.deleteAdminMenuOne(idx);}
    public int adminMenuCount(String menu){return dao.adminMenuCount(menu);}


}

package com.end.demo.service.cms;

import com.end.demo.dao.cms.IAdminDAO;
import com.end.demo.dao.cms.IPageManageDAO;
import com.end.demo.vo.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Service
public class PageManageService {

    @Autowired
    TransactionTemplate transactionTemplate; //트랜잭션 설정

    @Autowired
    IPageManageDAO dao;

    public List<UserMenuVO> selectUserMenu(){return dao.selectUserMenu();}
    public void insertUserMenu(HashMap<String, String> userMenuParam){dao.insertUserMenu(userMenuParam);}
    public void updateUserMenu(UserMenuVO userMenuVO){dao.updateUserMenu(userMenuVO);}
    public UserMenuVO getUserMenu(String node){return dao.getUserMenu(node);}
    public void updateUserMenuOne(UserMenuVO userMenuVO){dao.updateUserMenuOne(userMenuVO);}
    public void deleteUserMenuOne(int idx){dao.deleteUserMenuOne(idx);}
}

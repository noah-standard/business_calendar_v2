package com.end.demo.service.cms;

import com.end.demo.dao.cms.IAdminLoginDAO;
import com.end.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    @Autowired
    IAdminLoginDAO dao;

    public AdminVO getAdmin(String userid) {
        return dao.getAdmin(userid);
    }
}

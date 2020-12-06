package com.end.demo.dao.cms;


import com.end.demo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IAdminMenuDAO {

    public void insertAdminMenu(HashMap<String, String> adminMenuParam);
    public List<AdminMenuVO> selectAdminMenu();
    public List<AdminMenuVO> selectAdminMenuOne(String menu);
    public void updateAdminMenu(AdminMenuVO adminMenuVO);
    public AdminMenuVO selectOneAdminMenu(String node);
    public void updateAdminMenuOne(AdminMenuVO adminMenuVO);
    public void deleteAdminMenuOne(String idx);
    public int adminMenuCount(String menu);
}
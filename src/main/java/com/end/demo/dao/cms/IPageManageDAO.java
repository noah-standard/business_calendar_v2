package com.end.demo.dao.cms;


import com.end.demo.vo.*;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IPageManageDAO {
    public List<UserMenuVO> selectUserMenu();
    public void insertUserMenu(HashMap<String, String> userMenuParam);
    public void updateUserMenu(UserMenuVO userMenuVO);
    public UserMenuVO getUserMenu(String node);
    public void updateUserMenuOne(UserMenuVO userMenuVO);
    public void deleteUserMenuOne(int idx);
}
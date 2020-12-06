package com.end.demo.dao.cms;


import com.end.demo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IBBSManageDAO {
    public BBSManageVO getBBSManageCode(String code);
    public void insertBBSManage(BBSManageVO bbsManageVO);
    public void updateRandomIdx();
    public int getRandomIdx();
    public int getTotalBBSManage();
    public List<BBSManageVO> selectBBSManage();
    public List<HashMap<String,String>> selectGroupBBSManage();
    public BBSManageVO getBBSManage(String idx);
    public void updateBBSManage(BBSManageVO bbsManageVO);
    public void deleteBBSManage(String idx);

}
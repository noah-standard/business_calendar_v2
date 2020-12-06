package com.end.demo.dao.cms;


import com.end.demo.vo.*;
import com.end.demo.vo.BBSManageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface IAdminDAO {



    public int getTotalBBSManage();
    public List<BBSManageVO> selectBBSManage();
    public List<HashMap<String,String>> selectGroupBBSManage();
    public BBSManageVO getBBSManage(String idx);
    public void updateBBSManage(BBSManageVO bbsManageVO);
    public void deleteBBSManage(String idx);
    public String getType(String code);



    public int countBBSData(String search_order,String keyword, String code);
    public BBSDataVO getBBSData(long idx);
    public void deleteBBSData(long idx);
    public void updateBBS(BBSDataVO BBSDataVO);
}
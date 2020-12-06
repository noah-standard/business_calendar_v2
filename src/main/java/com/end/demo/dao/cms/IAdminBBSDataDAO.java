package com.end.demo.dao.cms;


import com.end.demo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IAdminBBSDataDAO {
    public List<BBSDataVO> selectBBSData(int start, int end, String search_order, String keyword, String code, int list_order);
    public void updateBBSData(BBSDataVO BBSDataVO);
    public void insertBBSData(BBSDataVO bbsVO);
    public int countBBSData(String search_order,String keyword, String code);
    public BBSDataVO getBBSData(long idx);
    public void updateBBSDataReadCnt(long idx);
    public void deleteBBSData(long idx);

}
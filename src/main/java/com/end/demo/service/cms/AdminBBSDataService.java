package com.end.demo.service.cms;

import com.end.demo.dao.cms.IAdminBBSDataDAO;
import com.end.demo.lib.Util;
import com.end.demo.service.UserService;
import com.end.demo.vo.BBSDataVO;
import com.end.demo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class AdminBBSDataService {

    @Autowired
    IAdminBBSDataDAO dao;

    public void insertBBSData(BBSDataVO bbsVO){
        bbsVO.setReg_date(Util.getDate("timestamp"));
        dao.insertBBSData(bbsVO);
    }
    public List<BBSDataVO> selectBBSData(int start, int end, String search_order, String keyword, String code, int list_order){return dao.selectBBSData(start,end,search_order,keyword,code,list_order);}
    public int countBBSData(String search_order,String keyword,String code){return dao.countBBSData(search_order,keyword,code); }
    public BBSDataVO getBBSData(long idx){return dao.getBBSData(idx);}
    public void updateBBSDataReadCnt(long idx){dao.updateBBSDataReadCnt(idx);}
    public void deleteBBSData(long idx){dao.deleteBBSData(idx);}
    public void updateBBSData(BBSDataVO bbsVO){dao.updateBBSData(bbsVO);}


}

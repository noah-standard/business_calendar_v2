package com.end.demo.service.cms;

import com.end.demo.dao.cms.IBBSManageDAO;
import com.end.demo.vo.BBSManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Service
public class BBSManageService {

    @Autowired
    IBBSManageDAO dao;

    public BBSManageVO getBBSManageCode(String code){return dao.getBBSManageCode(code);}
    public void insertBBSManage(BBSManageVO bbsManageVO){
        if(bbsManageVO.getBbs_type().equals("gallery")){
            bbsManageVO.setList_gallery(bbsManageVO.getList_height());
            bbsManageVO.setList_height(0);
        }else{
            bbsManageVO.setList_gallery(0);
        }
        bbsManageVO.setCode(bbsManageVO.getBbs_type() +"_"+dao.getRandomIdx());
        dao.updateRandomIdx();
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        String format_time1 = format1.format(time.getTime());

        bbsManageVO.setBbs_comment("0");
        bbsManageVO.setReg_date(format_time1);
        bbsManageVO.setDel_flag("0");
        bbsManageVO.setMemo1("");
        bbsManageVO.setMemo2("");
        bbsManageVO.setMemo3("");
        bbsManageVO.setMemo4("");

        dao.insertBBSManage(bbsManageVO);
    }
    public int getTotalBBSManage(){return dao.getTotalBBSManage();}
    public List<BBSManageVO> selectBBSManage(){return dao.selectBBSManage();}
    public List<HashMap<String,String>> selectGroupBBSManage(){return dao.selectGroupBBSManage();}
    public BBSManageVO getBBSManage(String idx){return dao.getBBSManage(idx);}
    public void updateBBSManage(BBSManageVO bbsManageVO){dao.updateBBSManage(bbsManageVO);}
    public void deleteBBSManage(String idx){dao.deleteBBSManage(idx);}
}

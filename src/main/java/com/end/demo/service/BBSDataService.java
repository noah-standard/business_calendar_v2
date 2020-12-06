package com.end.demo.service;

import com.end.demo.dao.IBBSDataDAO;
import com.end.demo.vo.BBSDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BBSDataService {

    @Autowired
    IBBSDataDAO dao;

    public BBSDataVO getBBSData(int idx){ return dao.getBBSData(idx);}
}

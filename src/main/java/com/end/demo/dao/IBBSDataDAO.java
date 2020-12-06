package com.end.demo.dao;


import com.end.demo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IBBSDataDAO {
    public BBSDataVO getBBSData(int idx);
}
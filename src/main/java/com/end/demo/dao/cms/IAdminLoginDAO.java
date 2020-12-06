package com.end.demo.dao.cms;

import com.end.demo.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IAdminLoginDAO {
    public AdminVO getAdmin(String userid);
}

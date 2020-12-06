package com.end.demo.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String rn;
    private String idx;
    private String userid;
    private String name;
    private String password;
    private String mem_level;
    private String phone1;
    private String phone2;
    private String zip;
    private String addr1;
    private String addr2;
    private String del_date;
    private String del_flag;
    private String reg_date;
    private String reg_ip;
}

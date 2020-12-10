package com.end.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberVO {
    private String rn;
    private String idx;
    @NotBlank
    private String userid;
    @NotBlank
    private String name;
    private String password;
    @NotBlank
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

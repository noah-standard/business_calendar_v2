package com.end.demo.vo.join;

import lombok.Data;

@Data
public class CalendarMemberVO {
    private String rn;
    private int idx;
    private int member_idx;
    private String userid;
    private String name;
    private String type;
    private String s_date;
    private String e_date;
    private String content;
    private int state;
    private int del_flag;
    private String reg_date;
    private String reg_ip;
}

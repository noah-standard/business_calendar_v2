package com.end.demo.vo;

import lombok.Data;

@Data
public class CalendarVO {
    private String rn;
    private int idx;
    private int member_idx;
    private String userid;
    private String name;
    private String title;
    private String type;
    private String s_date;
    private String e_date;
    private String content;
    private int del_flag;
    private int state;
    private String reg_date;
    private String color; // color table
    private String nickname; //join object
    private String start; //xhr object
    private String end; //xhr object
}


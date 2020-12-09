package com.end.demo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CalendarVO {
    private String rn;
    private int idx;

    @NotBlank @Min(0)
    private int member_idx;
    @NotBlank
    private String userid;
    @NotBlank
    private String name;
    private String title;
    @NotBlank
    private String type;
    private String s_date;
    private String e_date;
    @NotBlank
    private String content;
    private int del_flag;
    private int state;
    private String reg_date;
    private String color; // color table
    private String nickname; //join object
    private String start; //xhr object
    private String end; //xhr object
}


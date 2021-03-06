package com.end.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class HolidayVO {
    private String idx;
    private String year;
    private String holi_flag;
    private String date_kind;
    private String locdate_name;
    @NotBlank
    private String locdate;
    private String locdate_min;
    private String locdate_max;
    private String reg_date;
    private String reg_ip;
}

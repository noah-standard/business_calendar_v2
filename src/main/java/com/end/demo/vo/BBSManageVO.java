package com.end.demo.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class BBSManageVO {
    private long idx;
    @NotBlank
    private String name;
    private String code;
    private String bbs_type;
    private String bbs_pds;
    private String bbs_comment;
    private String bbs_read;
    private String bbs_write;
    private String bbs_modify;
    private int list_gallery;
    @Min(0)
    private int list_height;
    @Min(0)
    private int list_page;
    private String memo1;
    private String memo2;
    private String memo3;
    private String memo4;
    private String del_flag;
    private String reg_date;
}

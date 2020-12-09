package com.end.demo.vo;

import lombok.Data;

@Data
public class BBSManageVO {
    private long idx;
    private String name;
    private String code;
    private String bbs_type;
    private String bbs_pds;
    private String bbs_comment;
    private String bbs_read;
    private String bbs_write;
    private String bbs_modify;
    private int list_gallery;
    private int list_height;
    private int list_page;
    private String memo1;
    private String memo2;
    private String memo3;
    private String memo4;
    private String del_flag;
    private String reg_date;
}

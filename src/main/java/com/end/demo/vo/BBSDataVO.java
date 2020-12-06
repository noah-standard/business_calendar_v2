package com.end.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class BBSDataVO {
    private long rn;
    private long idx;
    private String code;
    private String subject;
    private String writer;
    private String password;
    private String email;
    private int notice;
    private long ref;
    private long ref_step;
    private long ref_level;
    private String content;

    @Null(message = "파일이 없습니다.")
    private String hid_file;
    @Null(message = "파일이 없습니다.")
    private String bbs_file0;
    @Null(message = "파일이 없습니다.")
    private String bbs_file1;
    @Null(message = "파일이 없습니다.")
    private String bbs_file2;
    private long read_cnt;
    private int del_flag;
    private String reg_date;
}

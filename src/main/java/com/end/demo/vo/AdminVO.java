package com.end.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdminVO {
    @NotBlank(message = "아이디와 비밀번호는 필수 입력 값입니다.")
    private String userid;
    private String name;
    @NotBlank(message = "아이디와 비밀번호는 필수 입력 값입니다.")
    private String password;
    private String authority;
    private String phone1;
    private String phone2;
    private String zip;
    private String add1;
    private String add2;

}

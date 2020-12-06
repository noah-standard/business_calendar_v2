package com.end.demo.vo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class UserVO {
    private int idx;
    private String userid;
    private String name;
    private String password;
    private String authority;
    private String phone1;
    private String phone2;
    private String zip;
    private String add1;
    private String add2;
}

package com.end.demo.vo.param;

import lombok.Data;

import javax.validation.constraints.Null;

@Data
public class BBSPagerVO {

    BBSPagerVO(){
        this.curPage = 1;
        this.search_order = "all";
        this.keyword = "";
        this.list_scale = 0;
        this.list_order = 0;
    }

    private String code;
    private String mode;
    private int curPage;
    private String search_order;
    private String keyword;
    private int idx;
    private int list_scale;
    private int list_order;
}

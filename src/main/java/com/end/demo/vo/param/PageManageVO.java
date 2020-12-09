package com.end.demo.vo.param;

import com.end.demo.lib.Util;
import lombok.Data;

@Data
public class PageManageVO {

    private int cate;
    private String name;
    private String link;
    private int state;
    private String ip;

    PageManageVO(){
        this.ip = Util.getIp();
    }
}

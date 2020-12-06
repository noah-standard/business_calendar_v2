package com.end.demo.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AdminMenuVO {
    String idx;
    String ref;
    String ref_step;
    String ref_level;
    String name;
    String link;
    String menu_target;
    String menu_custom;
    String state;
    String del_flag;
    String reg_date;
    String node;
    String node_parent;
    String node_position;
    ArrayList nodes_depth1;
}

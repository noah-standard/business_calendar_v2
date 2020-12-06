package com.end.demo.controllor.rest;

import com.end.demo.service.cms.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CalendarRestController {

    @Autowired
    CalendarService calendarService;

    @RequestMapping("/cms/ajax/state")
    public void memberChkAjax(@RequestParam int idx, @RequestParam int state){
        HashMap<String,Integer> query = new HashMap<String, Integer>();
        query.put("idx",idx);
        query.put("state",state);
        calendarService.updateState(query);
    }
}

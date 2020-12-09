package com.end.demo.controllor.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms")
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "cms/index";
    }

}
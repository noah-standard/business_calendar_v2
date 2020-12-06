package com.end.demo.controllor.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cms")
public class IndexController {

    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cms/index");
        return mv;
    }

}
package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.vo.AdminMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cms/ajax/admin_config")
public class AdminMenuRestControllor {

    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("menu")
    public AdminMenuVO getAdminMenu(@RequestParam String node) {
        return  adminMenuService.selectOneAdminMenu(node);
    }

    @PostMapping("menu")
    public ModelAndView updateAdminMenuOne(@ModelAttribute AdminMenuVO adminMenuVO) {
        ModelAndView modelAndView = new ModelAndView();
        adminMenuService.updateAdminMenuOne(adminMenuVO);
        modelAndView.setViewName("redirect:/cms/admin_config/menu");
        return modelAndView;
    }

    @GetMapping("menu/delete")
    public ModelAndView deleteAdminMenuOne(@RequestParam String idx){
        ModelAndView modelAndView = new ModelAndView();
        adminMenuService.deleteAdminMenuOne(idx);
        modelAndView.setViewName("redirect:/cms/admin_config/menu");
        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView adminMenuAjax(@RequestParam String menu, Model model){
        ModelAndView modelAndView = new ModelAndView();
        int adminMenuCnt = adminMenuService.adminMenuCount(menu);
        modelAndView.addObject("menuList", adminMenuService.selectAdminMenuOne(menu));
        modelAndView.addObject("menu_cnt", adminMenuCnt);
        modelAndView.setViewName("/cms/menu_ajax");
        return modelAndView;
    }
}

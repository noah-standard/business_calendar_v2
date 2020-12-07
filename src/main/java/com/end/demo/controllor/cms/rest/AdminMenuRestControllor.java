package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.vo.AdminMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cms/ajax/admin_config")
public class AdminMenuRestControllor {

    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("menu")
    public AdminMenuVO getAdminMenu(@RequestParam String node) {
        return  adminMenuService.selectOneAdminMenu(node);
    }

    @RequestMapping(value = "menu",method = RequestMethod.POST)
    public void updateAdminMenuOne(HttpServletResponse response, AdminMenuVO adminMenuVO) throws IOException {
        adminMenuService.updateAdminMenuOne(adminMenuVO);
        response.sendRedirect("/cms/admin_config/menu");
    }

    @RequestMapping(value = "menu/delete")
    public void deleteAdminMenuOne(HttpServletResponse response, @RequestParam String idx) throws IOException {
        adminMenuService.deleteAdminMenuOne(idx);
        response.sendRedirect("/cms/admin_config/menu");
    }

    @RequestMapping(value = "home")
    public ModelAndView adminMenuAjax(@RequestParam String menu){
        int adminMenuCnt = adminMenuService.adminMenuCount(menu);
        ModelAndView mv = new ModelAndView();
        mv.addObject("menuList", adminMenuService.selectAdminMenuOne(menu));
        mv.addObject("menu_cnt", adminMenuCnt);
        mv.setViewName("/cms/menu_ajax");
        return mv;
    }
}

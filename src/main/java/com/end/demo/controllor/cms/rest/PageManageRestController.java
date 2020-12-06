package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.AdminMenuVO;
import com.end.demo.vo.UserMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cms/page_manage/ajax")
public class PageManageRestController {

    @Autowired
    PageManageService pageManageService;

    @RequestMapping(value = "")
    public UserMenuVO getAdminMenu(@RequestParam String node) {
        return pageManageService.getUserMenu(node);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public void updateAdminMenuOne(HttpServletResponse response, UserMenuVO userMenuVO) throws IOException {
        pageManageService.updateUserMenuOne(userMenuVO);
        response.sendRedirect("/cms/page_manage");
    }

    @RequestMapping(value = "/delete.do")
    public void deleteAdminMenuOne(HttpServletResponse response, @RequestParam int idx) throws IOException {
        pageManageService.deleteUserMenuOne(idx);
        response.sendRedirect("/cms/page_manage");
    }

}

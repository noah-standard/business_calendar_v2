package com.end.demo.controllor.cms;

import com.end.demo.lib.Util;
import com.end.demo.service.cms.AdminService;
import com.end.demo.service.cms.PageManageService;
import com.end.demo.vo.AdminMenuVO;
import com.end.demo.vo.UserMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cms/page_manage")
public class PageManageController {

    @Autowired
    PageManageService pageManageService;

    @RequestMapping("")
    public ModelAndView userPageManage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("menu_list",pageManageService.selectUserMenu());
        mv.addObject("menu", "user");
        mv.addObject("page", "menu_user");
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value={""},method = RequestMethod.POST)
    public ModelAndView writeUserPage(@RequestParam String cate, @RequestParam String name, @RequestParam String link, @RequestParam String state ) {
        HashMap<String, String> userMenuParam = new HashMap<String, String>();
        userMenuParam.put("cate", cate);
        userMenuParam.put("name", name);
        userMenuParam.put("link", link);
        userMenuParam.put("state", state);
        userMenuParam.put("ip", Util.getIp());
        pageManageService.insertUserMenu(userMenuParam);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/cms/page_manage");
        return mv;
    }

    @RequestMapping(value={"/edit.do"} ,produces = "application/json; charset=utf8")
    public String editUserMenu(@RequestBody Map<String, Object> param) {
        UserMenuVO userMenuVO = new UserMenuVO();
        userMenuVO.setNode((String)param.get("node"));
        userMenuVO.setNode_parent((String)param.get("node_parent"));
        userMenuVO.setNode_position((String)param.get("node_position"));
        userMenuVO.setNodes_depth1((ArrayList) param.get("nodes_depth1"));

        pageManageService.updateUserMenu(userMenuVO);
        return "redirect:/cms/page_manage";
    }

}
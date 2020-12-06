package com.end.demo.controllor.cms;

import com.end.demo.service.cms.AdminMenuService;
import com.end.demo.service.cms.AdminService;
import com.end.demo.vo.AdminMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/cms/admin_config")
public class AdminMenuController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AdminMenuService adminMenuService;

    @RequestMapping("menu")
    public ModelAndView getConfig() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("menu_list",adminMenuService.selectAdminMenu());
        mv.addObject("menu", "admin_config");
        mv.addObject("page", "menu_admin");
        mv.setViewName("cms/template");
        return mv;
    }

    @RequestMapping(value={"menu"},method = RequestMethod.POST)
    public ModelAndView postConfig(@RequestParam String cate, @RequestParam String name,@RequestParam String link) {

        HashMap<String, String> adminMenuParam = new HashMap<String, String>();
        adminMenuParam.put("cate", cate);
        adminMenuParam.put("name", name);
        adminMenuParam.put("link", link);

        //IP
        InetAddress local;
        try {
            local = InetAddress.getLocalHost();
            String ip = local.getHostAddress();
            adminMenuParam.put("ip",ip);
        } catch (UnknownError | UnknownHostException e1) {
            e1.printStackTrace();
        }

        adminMenuService.insertAdminMenu(adminMenuParam);

        ModelAndView mv = new ModelAndView();
        mv.addObject("menu", "menu_config");
        mv.addObject("page", "menu_admin");
        mv.setViewName("redirect:/cms/config");

        return mv;
    }

    @RequestMapping("menu/edit")
    public String editMenu(@RequestBody Map<String, Object> param) {

        AdminMenuVO adminMenuVO = new AdminMenuVO();
        adminMenuVO.setNode((String)param.get("node"));
        adminMenuVO.setNode_parent((String)param.get("node_parent"));
        adminMenuVO.setNode_position((String)param.get("node_position"));
        adminMenuVO.setNodes_depth1((ArrayList) param.get("nodes_depth1"));
        adminMenuService.updateAdminMenu(adminMenuVO);

        return "redirect:menu";
    }
}
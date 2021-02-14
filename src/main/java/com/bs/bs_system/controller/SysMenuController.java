package com.bs.bs_system.controller;

import com.bs.bs_system.service.SysMenuService;
import com.bs.bs_system.vo.SysMenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单controller
 */
@RestController
@RequestMapping("/SysMenuController")
@Slf4j
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/qryMenu")
    public Map<String, Object> qryMenu() {
        Map<String, Object> res = new HashMap<>();
        try {
            log.info("菜单查询开始");
            List<SysMenuVO> listMap = sysMenuService.qryMenu();
//            String homeInfo = "{" +
//                    "'title': '首页'," +
//                    "'href': '/welcome'" +
//                    "}";
            //首页跳转
            Map<String, Object> homeInfo = new HashMap<>();
            homeInfo.put("title", "首页");
            homeInfo.put("href", "/welcome");
//            String logoInfo = "{" +
//                    "'title': '后台管理系统'," +
//                    "'image':'images/logo.png'," +
//                    "'href': ''" +
//                    "}";
            //标题logo
            Map<String, Object> logoInfo = new HashMap<>();
            logoInfo.put("title", "后台管理系统");
            logoInfo.put("image", "images/logo.png");
            logoInfo.put("href", "");
            //菜单
            List<Map<String, Object>> menuInfo = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("title", "常规管理");
            map.put("icon", "fa fa-address-book");
            map.put("href", "");
            map.put("target", "_self");
            map.put("child", listMap);
            menuInfo.add(map);
            res.put("homeInfo", homeInfo);
            res.put("logoInfo", logoInfo);
            res.put("menuInfo", menuInfo);
        } catch (Exception e) {
            log.error("菜单查询出现异常");
            e.printStackTrace();
        }

        return res;
    }
}

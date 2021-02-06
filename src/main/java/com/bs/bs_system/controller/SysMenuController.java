package com.bs.bs_system.controller;

import com.alibaba.fastjson.JSONObject;
import com.bs.bs_system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            List<Map<String, Object>> listMap = sysMenuService.qryMenu();
            String homeInfo = "{" +
                    "'title': '首页'," +
                    "'href': '/welcome'" +
                    "}";
            String logoInfo = "{" +
                    "'title': '后台管理系统'," +
                    "'image':'images/logo.png'," +
                    "'href': ''" +
                    "}";
            res.put("homeInfo", JSONObject.parseObject(homeInfo));
            res.put("logoInfo", JSONObject.parseObject(logoInfo));
            res.put("menuInfo", listMap);
        } catch (Exception e) {
            log.error("菜单查询出现异常");
            e.printStackTrace();
        }

        return res;
    }
}

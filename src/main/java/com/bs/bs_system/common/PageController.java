package com.bs.bs_system.common;

import lombok.Value;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Slf4j
@Controller
public class PageController {

    /**
     * 首页
     */
    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }
}

package com.bs.bs_system.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Slf4j
@Controller
public class PageController {

    /**
     * 通用页面跳转
     *
     * @param url
     * @return
     */
    @RequestMapping("/{url}")
    public String page(@PathVariable("url") String url) {
        return url;
    }


}

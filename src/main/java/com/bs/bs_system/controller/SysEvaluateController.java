package com.bs.bs_system.controller;

import com.bs.bs_system.service.SysEvaluateService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 评价管理
 */
@RestController
@Slf4j
@RequestMapping("/system")
public class SysEvaluateController {

    @Autowired
    private SysEvaluateService sysEvaluateService;

    /**
     * 分页查询留言信息
     *
     * @param limit
     * @param userName
     * @return
     */
    @RequestMapping("/qrySysEvaluateInfo")
    public DataTableVo<Map> qrySysEvaluateInfo(Long limit, String userName) {
        log.info("评价查询");
        DataTableVo<Map> dataTableVo = sysEvaluateService.qrySysEvaluateInfo(limit, userName);

        return dataTableVo;
    }

    /**
     * 添加留言
     *
     * @param request
     * @return
     */

    @RequestMapping("/insertSysEvaluateInfo")
    public DataTableVo insertSysEvaluateInfo(HttpServletRequest request) {

        DataTableVo<Map> dataTableVo = sysEvaluateService.insertSysEvaluateInfo(request);

        return dataTableVo;
    }

    @RequestMapping("/deleteSysEvaluate")
    public DataTableVo deleteSysEvaluate(String id) {
        DataTableVo dataTableVo = sysEvaluateService.deleteSysEvaluate(id);
        return dataTableVo;

    }

}

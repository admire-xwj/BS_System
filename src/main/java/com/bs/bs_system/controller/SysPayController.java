package com.bs.bs_system.controller;

import com.bs.bs_system.service.SysPayService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 薪酬管理
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class SysPayController {

    @Autowired
    private SysPayService sysPayService;


    /**
     * 分页查询薪资
     *
     * @param limit
     * @param userName
     * @return
     */
    @RequestMapping("/qrySysPayInfo")
    public DataTableVo<Map> qrySysPayInfo(Long limit, String userName) {
        log.info("分页查询部门信息开始");
        DataTableVo<Map> dataTableVo = sysPayService.qrySysPayInfo(limit, userName);
        return dataTableVo;
    }
}

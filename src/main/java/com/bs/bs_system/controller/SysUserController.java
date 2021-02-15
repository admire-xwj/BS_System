package com.bs.bs_system.controller;

import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.service.SysUserService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/system")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询系统用户
     *
     * @return
     */
    @RequestMapping("/qrySysUserInfo")
    public DataTableVo<Map> qrySysUserInfo(Long limit, String userName, String sex, String deptId, String userType) {
        log.info("用户管理查询开始" + limit + userName + sex);
        DataTableVo<Map> dataTableVo = sysUserService.qrySysUserInfo(limit, userName, sex, deptId, userType);
        return dataTableVo;
    }

    @RequestMapping("/deleteSysuser")
    public DataTableVo deleteSysuser(String userId) {
        log.info("用户删除开始");
        DataTableVo dataTableVo = new DataTableVo();
        dataTableVo.setCode("0");
        dataTableVo.setMsg("ch");
        return dataTableVo;
    }
}

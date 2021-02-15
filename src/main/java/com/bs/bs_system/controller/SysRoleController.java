package com.bs.bs_system.controller;

import com.bs.bs_system.entity.SysRole;
import com.bs.bs_system.service.SysRoleService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/system")
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页查询角色信息
     *
     * @param limit
     * @param roleName
     * @return
     */
    @RequestMapping("/qrySysRoleInfo")
    public DataTableVo<SysRole> qrySysRoleInfo(Long limit, String roleName) {
        log.info("角色查询开始");
        DataTableVo<SysRole> dataTableVo = sysRoleService.qrySysUserInfo(limit, roleName);
        return dataTableVo;
    }


    /**
     * 角色编辑（回显角色信息）
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/selectOneSysRole")
    public SysRole selectOneSysRole(String roleId) {
        log.info("回显角色信息开始");
        if (StringUtils.isEmpty(roleId)) {
            return null;
        }
        SysRole sysRole = sysRoleService.selectOneSysRole(roleId);
        return sysRole;
    }

    /**
     * 更新角色信息
     *
     * @return
     */
    @RequestMapping("/updateSysRoleInfo")
    public DataTableVo updateSysRoleInfo(HttpServletRequest request) {
        log.info("更新角色信息开始");
        String roleId = request.getParameter("roleId");
        DataTableVo dataTableVo = sysRoleService.updateSysRoleInfo(request);
        return dataTableVo;
    }

    /**
     * 删除角色信息
     *
     * @param roleId
     * @param DelFlag
     * @return
     */
    @RequestMapping("/deleteSysRole")
    public DataTableVo deleteSysRole(String roleId, String DelFlag) {
        log.info("删除角色信息开始");
        DataTableVo dataTableVo = sysRoleService.deleteSysRole(roleId, DelFlag);
        return dataTableVo;
    }

    /**
     * 恢复所有角色信息
     *
     * @param delFlag
     * @return
     */
    @RequestMapping("/recoverySysRole")
    public DataTableVo recoverySysRole(String delFlag) {
        log.info("恢复角色信息开始");
        DataTableVo dataTableVo = sysRoleService.recoverySysRole(delFlag);
        return dataTableVo;
    }
}

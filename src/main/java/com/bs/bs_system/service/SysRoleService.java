package com.bs.bs_system.service;

import com.bs.bs_system.entity.SysRole;
import com.bs.bs_system.vo.DataTableVo;

import javax.servlet.http.HttpServletRequest;

public interface SysRoleService {
    DataTableVo<SysRole> qrySysUserInfo(Long limit, String roleName);

    SysRole selectOneSysRole(String roleId);

    DataTableVo updateSysRoleInfo(HttpServletRequest request);

    DataTableVo deleteSysRole(String roleId, String DelFlag);

    DataTableVo recoverySysRole(String delFlag);
}

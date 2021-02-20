package com.bs.bs_system.service;

import com.bs.bs_system.vo.DataTableVo;

import java.util.Map;

public interface SysUserService {

    DataTableVo<Map> qrySysUserInfo(Long limit, String userName, String sex, String deptId, String userType);

    DataTableVo updateSysUserInfo(String oldPassword, String newPassword, String againPassword);
}

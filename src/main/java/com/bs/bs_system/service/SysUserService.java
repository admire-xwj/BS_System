package com.bs.bs_system.service;

import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.vo.DataTableVo;

public interface SysUserService {

    DataTableVo<SysUser> qrySysUserInfo(Long limit, String userName, String sex, String deptId, String userType);
}

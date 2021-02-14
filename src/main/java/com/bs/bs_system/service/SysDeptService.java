package com.bs.bs_system.service;

import com.bs.bs_system.entity.SysDept;
import com.bs.bs_system.vo.DataTableVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SysDeptService {
    DataTableVo<Map> qrySysDeptInfo(Long limit, String status, String deptName);

    SysDept selectOneSysDept(String deptId);

    DataTableVo updateSysDeptInfo(HttpServletRequest request);
}

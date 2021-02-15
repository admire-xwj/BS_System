package com.bs.bs_system.service;

import com.bs.bs_system.entity.SysDept;
import com.bs.bs_system.vo.DataTableVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SysDeptService {
    DataTableVo<Map> qrySysDeptInfo(Long limit, String status, String deptName);

    SysDept selectOneSysDept(String deptId);

    DataTableVo updateSysDeptInfo(HttpServletRequest request);

    List<Map<String, Object>> getTreeSelect(Long parentId);

    DataTableVo insertSysDeptInfo(HttpServletRequest request);

    DataTableVo deleteSysDept(String deptId);
}

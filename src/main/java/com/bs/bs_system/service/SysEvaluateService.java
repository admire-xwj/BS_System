package com.bs.bs_system.service;

import com.bs.bs_system.vo.DataTableVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SysEvaluateService {
    DataTableVo<Map> qrySysEvaluateInfo(Long limit, String userName);

    DataTableVo<Map> insertSysEvaluateInfo(HttpServletRequest request);

    DataTableVo deleteSysEvaluate(String id);
}

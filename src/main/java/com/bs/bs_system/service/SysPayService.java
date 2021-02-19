package com.bs.bs_system.service;

import com.bs.bs_system.vo.DataTableVo;

import java.util.Map;

public interface SysPayService {

    DataTableVo<Map> qrySysPayInfo(Long limit, String userName);
}

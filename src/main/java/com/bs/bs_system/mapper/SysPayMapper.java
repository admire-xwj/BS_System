package com.bs.bs_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.bs_system.entity.SysPay;

public interface SysPayMapper extends BaseMapper<SysPay> {
    int deleteByPrimaryKey(Long userId);

    int insert(SysPay record);

    int insertSelective(SysPay record);

    SysPay selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysPay record);

    int updateByPrimaryKey(SysPay record);
}
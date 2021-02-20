package com.bs.bs_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.bs_system.entity.SysEvaluate;

public interface SysEvaluateMapper extends BaseMapper<SysEvaluate> {
    int deleteByPrimaryKey(Long id);

    int insert(SysEvaluate record);

    int insertSelective(SysEvaluate record);

    SysEvaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysEvaluate record);

    int updateByPrimaryKey(SysEvaluate record);
}
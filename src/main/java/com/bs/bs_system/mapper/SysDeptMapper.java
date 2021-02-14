package com.bs.bs_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.bs_system.entity.SysDept;

public interface SysDeptMapper extends BaseMapper<SysDept> {
    int deleteByPrimaryKey(Long deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
}
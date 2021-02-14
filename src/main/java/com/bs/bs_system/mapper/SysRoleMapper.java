package com.bs.bs_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.bs_system.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
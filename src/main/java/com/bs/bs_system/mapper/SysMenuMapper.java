package com.bs.bs_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.bs_system.entity.SysMenu;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int deleteByPrimaryKey(Long menuId);

    @Override
    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}
package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.bs_system.entity.SysMenu;
import com.bs.bs_system.mapper.SysMenuMapper;
import com.bs.bs_system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 菜单查询
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> qryMenu() {

        QueryWrapper<SysMenu> qw = new QueryWrapper<>();
        qw.lambda().eq(SysMenu::getParentId, "0");
        List<SysMenu> list = sysMenuMapper.selectList(qw);
        for (SysMenu sysMenu : list) {

        }
        return null;
    }
}

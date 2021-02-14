package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bs.bs_system.entity.SysMenu;
import com.bs.bs_system.mapper.SysMenuMapper;
import com.bs.bs_system.service.SysMenuService;
import com.bs.bs_system.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<SysMenuVO> qryMenu() {
        List<SysMenuVO> res = new ArrayList<>();
        //查询一级菜单
        QueryWrapper<SysMenu> qw = new QueryWrapper<>();
        qw.lambda().eq(SysMenu::getParentId, "0");
        List<SysMenu> sysMenuListParent = sysMenuMapper.selectList(qw);
        Map<String, Object> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(sysMenuListParent)) {
            for (SysMenu sysMenuParent : sysMenuListParent) {
                SysMenuVO sysMenuVO = new SysMenuVO();
                sysMenuVO.setTitle(sysMenuParent.getMenuName());
                sysMenuVO.setIcon(sysMenuParent.getIcon());
                sysMenuVO.setHref(sysMenuParent.getUrl());
                sysMenuVO.setTarget(sysMenuParent.getTarget());
                //递归查询子菜单
                List<SysMenuVO> child = getChild(sysMenuParent.getMenuId());
                sysMenuVO.setChild(child);
                res.add(sysMenuVO);
            }
        }

        return res;
    }

    /**
     * 递归查询子菜单
     *
     * @param parentId
     * @return
     */
    private List<SysMenuVO> getChild(Long parentId) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysMenu::getParentId, parentId);
        List<SysMenu> sysMenuListSecond = sysMenuMapper.selectList(queryWrapper);
        List<SysMenuVO> sysMenuVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysMenuListSecond)) {
            for (SysMenu sysMenuSecond : sysMenuListSecond) {
                SysMenuVO sysMenuVOSecond = new SysMenuVO();
                sysMenuVOSecond.setTitle(sysMenuSecond.getMenuName());
                sysMenuVOSecond.setIcon(sysMenuSecond.getIcon());
                sysMenuVOSecond.setHref(sysMenuSecond.getUrl());
                sysMenuVOSecond.setTarget(sysMenuSecond.getTarget());
                //递归查询
                List<SysMenuVO> sysMenuVOList1 = getChild(sysMenuSecond.getMenuId());
                sysMenuVOSecond.setChild(sysMenuVOList1);
                sysMenuVOList.add(sysMenuVOSecond);
            }

        }
        return sysMenuVOList;
    }


}

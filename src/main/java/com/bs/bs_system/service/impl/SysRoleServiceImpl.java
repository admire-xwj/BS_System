package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.common.util.DateUtils;
import com.bs.bs_system.entity.SysRole;
import com.bs.bs_system.mapper.SysRoleMapper;
import com.bs.bs_system.service.SysRoleService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public DataTableVo<SysRole> qrySysUserInfo(Long limit, String roleName) {
        DataTableVo<SysRole> dataTableVo = new DataTableVo<>();
        try {

            QueryWrapper<SysRole> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(roleName)) {
                qw.lambda().eq(SysRole::getRoleName, roleName);
            }
            IPage<SysRole> page = new Page<>(1, limit);
            IPage<SysRole> sysRoleIPage = sysRoleMapper.selectPage(page, qw);
            List<SysRole> records = sysRoleIPage.getRecords();
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysRole -> {
                    if ("0".equals(sysRole.getStatus())) {
                        sysRole.setStatus("正常");
                    } else if ("1".equals(sysRole.getStatus())) {
                        sysRole.setStatus("停用");

                    }
                });
                msg = "搜索到" + records.size() + "条数据!";
            } else {
                msg = "没有搜索到相关数据!";
            }
            dataTableVo.setCode("0");
            dataTableVo.setCount(sysRoleIPage.getTotal());
            dataTableVo.setMsg(msg);
            dataTableVo.setData(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTableVo;
    }

    @Override
    public SysRole selectOneSysRole(String roleId) {
        log.info("角色id" + Long.parseLong(roleId));
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(Long.parseLong(roleId));
        return sysRole;
    }

    @Override
    public DataTableVo updateSysRoleInfo(HttpServletRequest request) {
        DataTableVo dataTableVo = new DataTableVo();
        String roleName = request.getParameter("roleName");
        if (StringUtils.isEmpty(roleName)) {
            dataTableVo.setCode("1");
            dataTableVo.setCount(0L);
            dataTableVo.setMsg("角色名称不能为空");
            dataTableVo.setData(null);
            return dataTableVo;
        }
        String remark = request.getParameter("remark");
        String roleId = request.getParameter("roleId");
        String status = request.getParameter("status");
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(Long.parseLong(roleId));
        sysRole.setStatus(StringUtils.equals("正常", status) ? "0" : "1");
        sysRole.setRoleName(roleName);
        sysRole.setRemark(remark);
        sysRole.setUpdateBy("admin");
        String formatDateToString = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd");
        Date date = DateUtils.formatStringToDate(formatDateToString, "yyyy-MM-dd");
        sysRole.setUpdateTime(date);

        int i = sysRoleMapper.updateById(sysRole);
        if (i == 1) {
            dataTableVo.setCode("0");
            dataTableVo.setCount(0L);
            dataTableVo.setMsg("保存成功");
            dataTableVo.setData(null);
        } else {
            dataTableVo.setCode("1");
            dataTableVo.setCount(0L);
            dataTableVo.setMsg("保存失败");
            dataTableVo.setData(null);
        }

        return dataTableVo;
    }
}

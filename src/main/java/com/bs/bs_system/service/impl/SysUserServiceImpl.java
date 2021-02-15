package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.entity.SysDept;
import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.mapper.SysDeptMapper;
import com.bs.bs_system.mapper.SysUserMapper;
import com.bs.bs_system.mapper.SysUserRoleMapper;
import com.bs.bs_system.service.SysUserService;
import com.bs.bs_system.vo.DataTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 用户信息查询(分页)
     *
     * @return
     */
    @Override
    public DataTableVo<Map> qrySysUserInfo(Long limit, String userName, String sex, String deptId, String userType) {
        DataTableVo<Map> dataTableVo = new DataTableVo<>();
        try {
            QueryWrapper<SysUser> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(userName)) {
                qw.lambda().eq(SysUser::getUserName, userName);
            }
            if (!StringUtils.isEmpty(sex)) {
                qw.lambda().eq(SysUser::getSex, sex);
            }
            if (!StringUtils.isEmpty(deptId)) {
                qw.lambda().eq(SysUser::getDeptId, deptId);
            }
            if (!StringUtils.isEmpty(userType)) {
                qw.lambda().eq(SysUser::getUserType, userType);
            }
            Page<SysUser> page = new Page<>(1, limit);
            IPage<SysUser> iPage = sysUserMapper.selectPage(page, qw);
            List<SysUser> records = iPage.getRecords();
            List<Map> mapList = new ArrayList<>();
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysUser -> {
                    Map map = new HashMap();
                    SysDept sysDept = sysDeptMapper.selectByPrimaryKey(Long.parseLong(sysUser.getDeptId()));
                    Map sysRoleMap = sysUserMapper.selectRoleInfo(sysUser.getUserId());
                    map.put("userId", sysUser.getUserId());
                    map.put("deptId", sysUser.getDeptId());
                    map.put("loginName", sysUser.getLoginName());
                    map.put("userName", sysUser.getUserName());
                    map.put("userType", StringUtils.equals("00", sysUser.getUserType()) ? "系统用户" : "注册用户");
                    map.put("email", sysUser.getEmail());
                    map.put("phonenumber", sysUser.getPhonenumber());
                    map.put("sex", StringUtils.equals("0", sysUser.getUserType()) ? "男" : "女");
                    map.put("status", StringUtils.equals("0", sysUser.getUserType()) ? "正常" : "停用");
                    map.put("loginIp", sysUser.getLoginIp());
                    map.put("loginDate", sysUser.getLoginDate());
                    map.put("pwdUpdateDate", sysUser.getPwdUpdateDate());
                    map.put("createBy", sysUser.getCreateBy());
                    map.put("createTime", sysUser.getCreateTime());
                    map.put("remark", sysUser.getRemark());
                    map.put("deptName", sysDept.getDeptName());
                    map.put("roleId", sysRoleMap.get("role_id"));
                    map.put("roleName", sysRoleMap.get("role_name"));
                    mapList.add(map);
                });
                msg = "搜索到" + records.size() + "条数据!";
            } else {
                msg = "没有搜索到相关数据!";
            }
            dataTableVo.setCode("0");
            dataTableVo.setCount(iPage.getTotal());
            dataTableVo.setMsg(msg);
            dataTableVo.setData(mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataTableVo;
    }
}

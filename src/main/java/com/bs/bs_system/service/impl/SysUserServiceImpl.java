package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.mapper.SysUserMapper;
import com.bs.bs_system.service.SysUserService;
import com.bs.bs_system.vo.DataTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 用户信息查询(分页)
     *
     * @return
     */
    @Override
    public DataTableVo<SysUser> qrySysUserInfo(Long limit, String userName, String sex, String deptId, String userType) {
        DataTableVo<SysUser> dataTableVo = new DataTableVo<>();
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
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysUser -> {
//                    Long deptId = sysUser.getDeptId();
                    if ("0".equals(sysUser.getSex())) {
                        sysUser.setSex("男");
                    } else {
                        sysUser.setSex("女");
                    }
                    if ("00".equals(sysUser.getUserType())) {
                        sysUser.setUserType("系统用户");
                    } else {
                        sysUser.setUserType("注册用户");
                    }
                    if ("0".equals(sysUser.getStatus())) {
                        sysUser.setStatus("正常");
                    } else {
                        sysUser.setStatus("停用");
                    }
                });
                msg = "搜索到" + records.size() + "条数据!";
            } else {
                msg = "没有搜索到相关数据!";
            }
            dataTableVo.setCode("0");
            dataTableVo.setCount(iPage.getTotal());
            dataTableVo.setMsg(msg);
            dataTableVo.setData(records);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataTableVo;
    }
}

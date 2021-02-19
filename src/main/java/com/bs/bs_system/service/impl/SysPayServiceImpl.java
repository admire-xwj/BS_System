package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.entity.SysPay;
import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.mapper.SysPayMapper;
import com.bs.bs_system.mapper.SysUserMapper;
import com.bs.bs_system.service.SysPayService;
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
public class SysPayServiceImpl implements SysPayService {

    @Autowired
    private SysPayMapper sysPayMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public DataTableVo<Map> qrySysPayInfo(Long limit, String userName) {

        DataTableVo<Map> dataTableVo = new DataTableVo<>();
        try {

            QueryWrapper<SysPay> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(userName)) {
                QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysUser::getUserName, userName);
                List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
                if (!CollectionUtils.isEmpty(sysUsers)) {
                    String userId = sysUsers.get(0).getUserId();
                    qw.lambda().eq(SysPay::getUserId, userId);
                }
            }
            IPage<SysPay> page = new Page<>(1, limit);
            IPage<SysPay> iPage = sysPayMapper.selectPage(page, qw);
            List<SysPay> records = iPage.getRecords();
            List<Map> listmap = new ArrayList<>();
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysPay -> {
                    Map<String, Object> map = new HashMap<>();
                    SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysPay.getUserId().toString());
                    map.put("userId", sysPay.getUserId());
                    map.put("userName", sysUser.getUserName());
                    map.put("basePay", sysPay.getBasePay());
                    map.put("bonus", sysPay.getBonus());
                    map.put("fiveInsur", sysPay.getFiveInsur());
                    map.put("otherDeduc", sysPay.getOtherDeduc());
                    map.put("otherWages", sysPay.getOtherWages());
                    map.put("orderNum", sysPay.getOrderNum());
                    map.put("createBy", sysPay.getCreateBy());
                    map.put("createTime", sysPay.getCreateTime());
                    map.put("remark", sysPay.getRemark());
                    listmap.add(map);

                });
                msg = "搜索到" + records.size() + "条数据!";
            } else {
                msg = "没有搜索到相关数据!";
            }
            dataTableVo.setCode("0");
            dataTableVo.setCount(iPage.getTotal());
            dataTableVo.setMsg(msg);
            dataTableVo.setData(listmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTableVo;
    }
}

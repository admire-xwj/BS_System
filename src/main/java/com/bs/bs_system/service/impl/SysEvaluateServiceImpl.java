package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.common.util.DateUtils;
import com.bs.bs_system.entity.SysEvaluate;
import com.bs.bs_system.entity.SysUser;
import com.bs.bs_system.mapper.SysEvaluateMapper;
import com.bs.bs_system.mapper.SysUserMapper;
import com.bs.bs_system.service.SysEvaluateService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class SysEvaluateServiceImpl implements SysEvaluateService {

    @Autowired
    private SysEvaluateMapper sysEvaluateMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public DataTableVo<Map> qrySysEvaluateInfo(Long limit, String userName) {

        DataTableVo<Map> dataTableVo = new DataTableVo<>();
        try {
            QueryWrapper<SysEvaluate> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(userName)) {
                QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(SysUser::getUserName, userName);
                List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
                if (!CollectionUtils.isEmpty(sysUsers)) {
                    String userId = sysUsers.get(0).getUserId();
                    qw.lambda().eq(SysEvaluate::getUserId, userId);
                }
            }
            qw.lambda().eq(SysEvaluate::getDelFlag,"0");
            IPage<SysEvaluate> page = new Page<>(1, limit);
            IPage<SysEvaluate> iPage = sysEvaluateMapper.selectPage(page, qw);
            List<SysEvaluate> records = iPage.getRecords();
            List<Map> listmap = new ArrayList<>();
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysEvaluate -> {
                    Map<String, Object> map = new HashMap<>();
                    SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysEvaluate.getUserId().toString());
                    map.put("id", sysEvaluate.getId());
                    map.put("userId", sysEvaluate.getUserId());
                    map.put("userName", sysUser.getUserName());
                    map.put("deptEvaluate", sysEvaluate.getDeptEvaluate());
                    map.put("conpanyEvaluate", sysEvaluate.getConpanyEvaluate());
                    map.put("selfEvaluate", sysEvaluate.getSelfEvaluate());
                    map.put("delFlag", sysEvaluate.getDelFlag());
                    map.put("createBy", sysEvaluate.getCreateBy());
                    map.put("createTime", sysEvaluate.getCreateTime());
                    map.put("remark", sysEvaluate.getRemark());
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

    @Override
    public DataTableVo<Map> insertSysEvaluateInfo(HttpServletRequest request) {
        DataTableVo<Map> dataTableVo = new DataTableVo<>();
        SysEvaluate sysEvaluate = new SysEvaluate();
        String userName = request.getParameter("userName");
        if (!StringUtils.isEmpty(userName)) {
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysUser::getUserName, userName);
            List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(sysUsers)) {
                String userId = sysUsers.get(0).getUserId();
                sysEvaluate.setUserId(Long.parseLong(userId));
            } else {
                dataTableVo.setCode("1");
                dataTableVo.setCount(0L);
                dataTableVo.setMsg("姓名不存在，请重新填写");
                dataTableVo.setData(null);
                return dataTableVo;
            }
        }
        String conpanyEvaluate = request.getParameter("conpanyEvaluate");
        String deptEvaluate = request.getParameter("deptEvaluate");
        String selfEvaluate = request.getParameter("selfEvaluate");
        String remark = request.getParameter("remark");

        sysEvaluate.setConpanyEvaluate(conpanyEvaluate);
        sysEvaluate.setDeptEvaluate(deptEvaluate);
        sysEvaluate.setSelfEvaluate(selfEvaluate);
        sysEvaluate.setRemark(remark);
        sysEvaluate.setCreateBy("admin");
        sysEvaluate.setUpdateBy("admin");
        String formatDateToString = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd");
        Date date = DateUtils.formatStringToDate(formatDateToString, "yyyy-MM-dd");
        sysEvaluate.setUpdateTime(date);
        sysEvaluate.setCreateTime(date);
        int row = sysEvaluateMapper.insert(sysEvaluate);
        if (row == 1) {
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

    @Override
    public DataTableVo deleteSysEvaluate(String id) {

        DataTableVo dataTableVo = new DataTableVo();
        SysEvaluate sysEvaluate = sysEvaluateMapper.selectByPrimaryKey(Long.parseLong(id));
        int row = 0;

        if (!ObjectUtils.isEmpty(sysEvaluate)) {
            sysEvaluate.setDelFlag("1");
            sysEvaluate.setUpdateBy("admin");
            String formatDateToString = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd");
            Date date = DateUtils.formatStringToDate(formatDateToString, "yyyy-MM-dd");
            sysEvaluate.setUpdateTime(date);
            row = sysEvaluateMapper.updateByPrimaryKey(sysEvaluate);
        }
        if (row == 1) {
            dataTableVo.setCode("0");
            dataTableVo.setCount(0L);
            dataTableVo.setMsg("删除成功");
            dataTableVo.setData(null);
        } else {
            dataTableVo.setCode("1");
            dataTableVo.setCount(0L);
            dataTableVo.setMsg("删除失败");
            dataTableVo.setData(null);
        }
        return dataTableVo;
    }
}

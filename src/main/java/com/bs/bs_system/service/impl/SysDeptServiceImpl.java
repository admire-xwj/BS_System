package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.common.util.DateUtils;
import com.bs.bs_system.entity.SysDept;
import com.bs.bs_system.mapper.SysDeptMapper;
import com.bs.bs_system.service.SysDeptService;
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
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public DataTableVo<Map> qrySysDeptInfo(Long limit, String status, String deptName) {
        DataTableVo<Map> dataTableVo = new DataTableVo<>();
        try {
            QueryWrapper<SysDept> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(deptName)) {
                qw.lambda().eq(SysDept::getDeptName, deptName);
            }
            if (!StringUtils.isEmpty(status)) {
                qw.lambda().eq(SysDept::getStatus, status);
            }
            IPage<SysDept> page = new Page<>(1, limit);
            IPage<SysDept> iPage = sysDeptMapper.selectPage(page, qw);
            List<SysDept> records = iPage.getRecords();
            List<Map> listmap = new ArrayList<>();
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysDept -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("deptId", sysDept.getDeptId());
                    map.put("deptName", sysDept.getDeptName());
                    map.put("orderNum", sysDept.getOrderNum());
                    map.put("leader", sysDept.getLeader());
                    map.put("phone", sysDept.getPhone());
                    map.put("email", sysDept.getEmail());
                    map.put("status", StringUtils.equals("0", sysDept.getStatus()) ? "正常" : "停用");
                    map.put("createBy", sysDept.getCreateBy());
                    map.put("createTime", sysDept.getCreateTime());
                    SysDept sysDept1 = sysDeptMapper.selectByPrimaryKey(sysDept.getParentId());
                    String parentDeptName = "";
                    if (!ObjectUtils.isEmpty(sysDept1)) {
                        parentDeptName = sysDept1.getDeptName();
                    }
                    map.put("parentDeptName", parentDeptName);
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
    public SysDept selectOneSysDept(String deptId) {
        QueryWrapper<SysDept> qw = new QueryWrapper<>();
        qw.lambda().eq(SysDept::getDeptId, deptId);
        SysDept sysDept = sysDeptMapper.selectOne(qw);
        return sysDept;
    }

    @Override
    public DataTableVo updateSysDeptInfo(HttpServletRequest request) {
        DataTableVo dataTableVo = new DataTableVo();
        SysDept sysDept = new SysDept();
        String deptId = request.getParameter("deptId");
        String deptName = request.getParameter("deptName");
        String parentDeptName = request.getParameter("parentDeptName");
        //父级部门是否存在校验
        Long parentId = 0L;
        if (!StringUtils.isEmpty(parentDeptName)) {
            QueryWrapper<SysDept> qw = new QueryWrapper<>();
            qw.lambda().eq(SysDept::getDeptName, parentDeptName);
            List<SysDept> sysDepts = sysDeptMapper.selectList(qw);
            if (CollectionUtils.isEmpty(sysDepts)) {
                dataTableVo.setCode("1");
                dataTableVo.setCount(0L);
                dataTableVo.setMsg("请选择正确的父级部门");
                dataTableVo.setData(null);
                return dataTableVo;
            } else {
                parentId = sysDepts.get(0).getParentId();
            }
        }
        String leader = request.getParameter("leader");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        sysDept.setDeptId(Long.parseLong(deptId));
        sysDept.setDeptName(deptName);
        sysDept.setParentId(parentId);
        sysDept.setStatus(StringUtils.equals("正常", status) ? "0" : "1");
        sysDept.setLeader(leader);
        sysDept.setPhone(phone);
        sysDept.setUpdateBy("admin");
        String formatDateToString = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd");
        Date date = DateUtils.formatStringToDate(formatDateToString, "yyyy-MM-dd");
        sysDept.setUpdateTime(date);
        int i = sysDeptMapper.updateById(sysDept);
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

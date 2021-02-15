package com.bs.bs_system.controller;

import com.bs.bs_system.entity.SysDept;
import com.bs.bs_system.service.SysDeptService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 */
@RestController
@Slf4j
@RequestMapping("/system")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/qrySysDeptInfo")
    public DataTableVo<Map> qrySysDeptInfo(Long limit, String status, String deptName) {
        log.info("分页查询部门信息开始");
        DataTableVo<Map> dataTableVo = sysDeptService.qrySysDeptInfo(limit, status, deptName);
        return dataTableVo;
    }

    @RequestMapping("/selectOneSysDept")
    public SysDept selectOneSysDept(String deptId) {
        log.info("部门回显开始");
        if (StringUtils.isEmpty(deptId)) {
            return null;
        }
        SysDept sysDept = sysDeptService.selectOneSysDept(deptId);
        return sysDept;
    }

    @RequestMapping("/updateSysDeptInfo")
    public DataTableVo updateSysDeptInfo(HttpServletRequest request) {
        log.info("更新部门信息开始");
        DataTableVo dataTableVo = sysDeptService.updateSysDeptInfo(request);
        return dataTableVo;
    }

    /**
     * 查询部门组件
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/getTreeSelect")
    public List<Map<String, Object>> getTreeSelect(Long parentId) {
        log.info("查询部门组件开始");
        List<Map<String, Object>> treeSelectList = sysDeptService.getTreeSelect(0L);
        return treeSelectList;

    }

    /**
     * 添加部门
     *
     * @param request
     * @return
     */
    @RequestMapping("/insertSysDeptInfo")
    public DataTableVo insertSysDeptInfo(HttpServletRequest request) {
        log.info("添加部门开始");
        DataTableVo dataTableVo = sysDeptService.insertSysDeptInfo(request);
        return dataTableVo;
    }

    /**
     * @param deptId
     * @return
     */
    @RequestMapping("/deleteSysDept")
    public DataTableVo deleteSysDept(String deptId) {
        log.info("删除部门开始");
        DataTableVo dataTableVo = sysDeptService.deleteSysDept(deptId);
        return dataTableVo;
    }
}

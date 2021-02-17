package com.bs.bs_system.controller;

import com.bs.bs_system.service.SysFileService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 档案管理
 */
@RestController
@Slf4j
@RequestMapping("/system")
public class SysFileController {

    @Autowired
    private SysFileService sysFileService;

    /**
     * 分页查询档案信息
     *
     * @param limit
     * @param deptId
     * @param fileType
     * @return
     */
    @RequestMapping("/qrySysFileInfo")
    public DataTableVo<Map> qrySysFileInfo(Long limit, String deptId, String fileType, String fileName) {
        log.info("分页查询档案信息开始");
        DataTableVo<Map> dataTableVo = sysFileService.qrySysFileInfo(limit, deptId, fileType, fileName);
        return dataTableVo;
    }

    @RequestMapping("/deleteSysFile")
    public DataTableVo deleteSysFile(String fileId) {
        log.info("删除部门开始");
        DataTableVo dataTableVo = sysFileService.deleteSysFile(fileId);
        return dataTableVo;
    }


    @RequestMapping("/fileUpload")
    public DataTableVo fileUpload(MultipartFile file, String deptId, String remark, String fileType) {
        DataTableVo dataTableVo = new DataTableVo();
        try {
            log.info("档案上传开始" + deptId);
            String filename = file.getOriginalFilename();
            dataTableVo = sysFileService.fileUpload(file, filename, deptId, remark, fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTableVo;
    }
}

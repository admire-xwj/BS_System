package com.bs.bs_system.controller;

import com.bs.bs_system.common.util.UploadUtils;
import com.bs.bs_system.service.SysFileService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * 档案管理
 */
@Controller
@Slf4j
@RequestMapping("/system")
public class SysFileController {

    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private UploadUtils uploadUtils;

    /**
     * 分页查询档案信息
     *
     * @param limit
     * @param deptId
     * @param fileType
     * @return
     */
    @RequestMapping("/qrySysFileInfo")
    @ResponseBody
    public DataTableVo<Map> qrySysFileInfo(Long limit, String deptId, String fileType, String fileName) {
        log.info("分页查询档案信息开始");
        DataTableVo<Map> dataTableVo = sysFileService.qrySysFileInfo(limit, deptId, fileType, fileName);
        return dataTableVo;
    }

    @RequestMapping("/deleteSysFile")
    @ResponseBody
    public DataTableVo deleteSysFile(String fileId) {
        log.info("删除部门开始");
        DataTableVo dataTableVo = sysFileService.deleteSysFile(fileId);
        return dataTableVo;
    }


    @RequestMapping("/fileUpload")
    @ResponseBody
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

    @RequestMapping("/downloadModel")
    public void mouldsDown(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        ServletOutputStream output = null;
        try {
            String fileName = httpServletRequest.getParameter("fileName");
            log.info(fileName);
            File directory = new File("src/main/resources/templates/temp/");

            String path = directory.getCanonicalPath();
            File file = new File(path + File.separator + fileName);
            if (!file.exists()) {
                httpServletRequest.getRequestDispatcher("/system/error").forward(httpServletRequest, response);
            }
            output = uploadUtils.downFileForMoulds(httpServletRequest, response, fileName, path);
        } catch (Exception e) {
            log.error("异常信息:{}", e);
            throw new RuntimeException("文件下载异常！");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Exception e) {
                    log.error("异常信息:{}", e);
                    throw new RuntimeException("文件下载异常！");
                }
            }
        }
    }
}

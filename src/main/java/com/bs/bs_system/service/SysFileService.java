package com.bs.bs_system.service;

import com.bs.bs_system.vo.DataTableVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SysFileService {

    DataTableVo<Map> qrySysFileInfo(Long limit, String deptId, String fileType, String fileName);

    DataTableVo deleteSysFile(String fileId);

    DataTableVo fileUpload(MultipartFile file, String filename, String deptId, String remark, String fileType);
}

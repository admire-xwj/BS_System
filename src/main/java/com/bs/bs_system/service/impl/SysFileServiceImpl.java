package com.bs.bs_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.bs_system.common.util.DateUtils;
import com.bs.bs_system.entity.SysDept;
import com.bs.bs_system.entity.SysFile;
import com.bs.bs_system.mapper.SysDeptMapper;
import com.bs.bs_system.mapper.SysFileMapper;
import com.bs.bs_system.service.SysFileService;
import com.bs.bs_system.vo.DataTableVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class SysFileServiceImpl implements SysFileService {

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public DataTableVo<Map> qrySysFileInfo(Long limit, String deptId, String fileType, String fileName) {
        DataTableVo<Map> dataTableVo = new DataTableVo<>();
        try {
            QueryWrapper<SysFile> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(deptId)) {
                qw.lambda().eq(SysFile::getDeptId, Long.parseLong(deptId));
            }
            if (!StringUtils.isEmpty(fileType)) {
                qw.lambda().eq(SysFile::getFileType, fileType);
            }
            if (!StringUtils.isEmpty(fileName)) {
                qw.like("file_name", fileName);
            }
            qw.lambda().eq(SysFile::getDelFlag, "0");
            IPage<SysFile> page = new Page<>(1, limit);
            IPage<SysFile> iPage = sysFileMapper.selectPage(page, qw);
            List<SysFile> records = iPage.getRecords();
            List<Map> mapList = new ArrayList<>();
            String msg = "";
            if (!CollectionUtils.isEmpty(records)) {
                records.forEach(sysFile -> {
                    Map map = new HashMap();
                    map.put("fileId", sysFile.getFileId());
                    map.put("fileName", sysFile.getFileName());
                    map.put("deptId", sysFile.getDeptId());
                    map.put("orderNum", sysFile.getOrderNum());
                    map.put("fileUrl", sysFile.getFileUrl());
                    map.put("fileType", sysFile.getFileType());
                    map.put("delFlag", sysFile.getDelFlag());
                    map.put("createBy", sysFile.getCreateBy());
                    map.put("createTime", sysFile.getCreateTime());
                    map.put("remark", sysFile.getRemark());
                    if (!ObjectUtils.isEmpty(sysFile.getDeptId())) {
                        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysFile.getDeptId());
                        map.put("deptName", sysDept.getDeptName());
                    }
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

    @Override
    public DataTableVo deleteSysFile(String fileId) {
        DataTableVo dataTableVo = new DataTableVo<>();

        SysFile sysFile = sysFileMapper.selectByPrimaryKey(Long.parseLong(fileId));
        sysFile.setDelFlag("1");
        sysFile.setUpdateBy("admin");
        String formatDateToString = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd");
        Date date = DateUtils.formatStringToDate(formatDateToString, "yyyy-MM-dd");
        sysFile.setUpdateTime(date);
        int row = sysFileMapper.updateByPrimaryKey(sysFile);

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

    @Override
    public DataTableVo fileUpload(MultipartFile file, String filename, String deptId, String remark, String fileType) {

        DataTableVo dataTableVo = new DataTableVo();
        try {
            File directory = new File("src/main/resources/templates/temp/");
            String path = directory.getCanonicalPath();
            log.info(path);
            SysFile sysFile = new SysFile();
            sysFile.setFileName(filename);
            sysFile.setDeptId(Long.parseLong(deptId));
            sysFile.setFileUrl(path);
            sysFile.setFileType(fileType);
            sysFile.setDelFlag("0");
            sysFile.setCreateBy("admin");
            String formatDateToString = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd");
            Date date = DateUtils.formatStringToDate(formatDateToString, "yyyy-MM-dd");
            sysFile.setCreateTime(date);
            sysFile.setRemark(remark);
            int row = sysFileMapper.insert(sysFile);
            if (row == 1) {
                file.transferTo(new File(path + File.separator + filename));
                dataTableVo.setCode("0");
                dataTableVo.setCount(0L);
                dataTableVo.setMsg("上传成功");
                dataTableVo.setData(null);
            } else {
                dataTableVo.setCode("1");
                dataTableVo.setCount(0L);
                dataTableVo.setMsg("上传失败");
                dataTableVo.setData(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dataTableVo;
    }
}

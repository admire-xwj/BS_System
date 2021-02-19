package com.bs.bs_system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_file
 *
 * @author
 */
@Data
public class SysFile implements Serializable {
    /**
     * 档案编号
     */
    @TableId(value = "file_id")
    private Long fileId;

    /**
     * 档案名称
     */
    private String fileName;

    /**
     * 所属部门
     */
    private Long deptId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 档案类型
     */
    private String fileType;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 上传者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}
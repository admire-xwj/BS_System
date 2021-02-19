package com.bs.bs_system.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_evaluate
 * @author 
 */
@Data
public class SysEvaluate implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 部门评价
     */
    private String deptEvaluate;

    /**
     * 公司评价
     */
    private String conpanyEvaluate;

    /**
     * 个人评价
     */
    private String selfEvaluate;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 创建者
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
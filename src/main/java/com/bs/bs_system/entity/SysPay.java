package com.bs.bs_system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_pay
 *
 * @author
 */
@Data
public class SysPay implements Serializable {
    /**
     * 员工id
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 基本工资
     */
    private Double basePay;

    /**
     * 奖金
     */
    private Double bonus;

    /**
     * 五险扣除
     */
    private Double fiveInsur;

    /**
     * 其他扣除
     */
    private Double otherDeduc;

    /**
     * 其它补助
     */
    private Double otherWages;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 上传者
     */
    private String createBy;

    /**
     * 上传时间
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
package com.bs.bs_system.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 数据表格vo对象
 */
@Data
public class DataTableVo<T> implements Serializable {
    private static final long serialVersionUID = 151641029692010047L;

    private String code;    //0 成功  1 失败
    private String msg;     //返回结果信息
    private Long count;  //数据表格总行数
    private List<T> data;   //返回结果数据
}

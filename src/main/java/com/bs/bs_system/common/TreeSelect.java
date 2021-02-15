package com.bs.bs_system.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 树形组件结果集
 */
@Data
public class TreeSelect implements Serializable {

    private static final long serialVersionUID = -5350955891473260003L;
    /**
     * 节点id
     */
    private String id;
    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点是否展开
     */
    private Boolean open;

    private Boolean checked;
    /**
     * 子节点
     */
    private List<TreeSelect> children;
}

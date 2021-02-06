package com.bs.bs_system.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.List;

@Data
public class SysMenuVO implements Serializable {

    private static final long serialVersionUID = 3625026754075187289L;
    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 页面跳转路径
     */
    private String href;

    /**
     * 属性
     */
    @Value("_self")
    private String target;

    /**
     * 子菜单集合
     */
    private List child;
}

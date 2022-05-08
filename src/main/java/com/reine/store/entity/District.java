package com.reine.store.entity;

import lombok.Data;

/**
 * 省市区数据实体类
 *
 * @author reine
 * 2022/5/8 8:27
 */
@Data
public class District {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 上级区域编号
     */
    private String parent;
    /**
     * 区域编号
     */
    private String code;
    /**
     * 区域名称
     */
    private String name;

}

package com.reine.store.vo;

import lombok.Data;

/**
 * @author reine
 * 2022/5/8 12:01
 */
@Data
public class AddressVo {
    /**
     * 收货地址id
     */
    private Integer aid;
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 省-名称
     */
    private String provinceName;
    /**
     * 市-名称
     */
    private String cityName;
    /**
     * 区-名称
     */
    private String areaName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 手机
     */
    private String phone;
    /**
     * 标签
     */
    private String tag;
    /**
     * 是否默认：0-不默认，1-默认
     */
    private Integer isDefault;

}

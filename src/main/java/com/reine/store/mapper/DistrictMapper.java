package com.reine.store.mapper;

import com.reine.store.entity.District;

import java.util.List;

/**
 * @author reine
 * 2022/5/8 8:54
 */
public interface DistrictMapper {

    /**
     * 根据父代号来查询区域信息
     *
     * @param parent 父代号
     * @return 对应的区域列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据编号查询名称
     *
     * @param code 编号
     * @return 名称
     */
    String findNameByCode(String code);
}

package com.reine.store.service;

import com.reine.store.entity.District;
import com.reine.store.vo.DistrictVo;

import java.util.List;

/**
 * @author reine
 * 2022/5/8 9:18
 */
public interface IDistrictService {

    /**
     * 根据父代号来查询区域信息
     *
     * @param parent 父代号
     * @return 对应的区域列表
     */
    List<DistrictVo> getByParent(String parent);

    /**
     * 根据编号查询名称
     *
     * @param code 编号
     * @return 名称
     */
    String getNameByCode(String code);
}

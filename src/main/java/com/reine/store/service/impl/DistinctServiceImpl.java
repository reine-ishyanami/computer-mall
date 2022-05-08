package com.reine.store.service.impl;

import com.reine.store.entity.District;
import com.reine.store.mapper.DistrictMapper;
import com.reine.store.service.IDistrictService;
import com.reine.store.vo.DistrictVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 省市区业务类
 *
 * @author reine
 * 2022/5/8 9:19
 */
@Service
public class DistinctServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 根据父代号来查询区域信息
     *
     * @param parent 父代号
     * @return 对应的区域列表
     */
    @Override
    public List<DistrictVo> getByParent(String parent) {
        List<District> districts = districtMapper.findByParent(parent);
        List<DistrictVo> list = districts.stream().map(district -> {
            DistrictVo districtVo = new DistrictVo();
            BeanUtils.copyProperties(district, districtVo);
            return districtVo;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * 根据编号查询名称
     *
     * @param code 编号
     * @return 名称
     */
    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}

package com.reine.store.controller;

import com.reine.store.entity.District;
import com.reine.store.service.IDistrictService;
import com.reine.store.util.JsonResult;
import com.reine.store.vo.DistrictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author reine
 * 2022/5/8 9:32
 */
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {

    @Autowired
    private IDistrictService districtService;

    /**
     * 根据父代号来查询区域信息
     * @param parent 父代号
     * @return 对应的区域列表
     */
    @RequestMapping
    public JsonResult<List<DistrictVo>> getByParent(String parent) {
        List<DistrictVo> districts = districtService.getByParent(parent);
        return new JsonResult<>(OK, districts);
    }

}

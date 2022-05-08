package com.reine.store.util;

import com.reine.store.vo.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 前端返回对象
 *
 * @author reine
 * 2022/5/6 17:16
 */
@Data
@NoArgsConstructor
public class JsonResult<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 数据
     */
    private E data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(ErrorCode e) {
        this.state = e.getCode();
        this.message = e.getMassage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

}

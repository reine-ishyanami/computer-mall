package com.reine.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类的基类
 *
 * @author reine
 * @since 2022/5/6 14:25
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createdUser;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改者
     */
    private String modifiedUser;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;


}

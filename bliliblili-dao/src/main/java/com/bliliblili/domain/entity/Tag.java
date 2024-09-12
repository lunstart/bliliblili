package com.bliliblili.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ author ljw
 * @ data 2024/9/12 15:51
 */
@Data
public class Tag {
    // 标签id
    private Long id;

    // 标签名称
    private String name;

    // 创建时间
    private Date createTime;
}

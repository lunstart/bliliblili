package com.bliliblili.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/20 16:14
 * @ 注释
 */
@Data
@Builder
public class UserLogin {
    // 主键
    private Long id;

    // 用户id
    private Long userId;

    // 登录类型 1PC 2手机
    private String type;

    // 最近登录时间
    private Date lastLoginTime;
}

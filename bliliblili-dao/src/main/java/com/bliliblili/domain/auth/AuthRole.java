package com.bliliblili.domain.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/9 20:13
 * @ 注释  权限控制角色
 */
@Data
public class AuthRole {
    // 主键
    private Long id;

    //角色名称
    private String name;

    //创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;
}

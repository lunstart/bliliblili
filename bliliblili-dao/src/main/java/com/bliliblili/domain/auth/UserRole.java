package com.bliliblili.domain.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/9 20:16
 * @ 注释 用户角色关联
 */
@Data
public class UserRole {

    // 主键
    private Long id;

    // 用户id
    private Long userId;

    // 角色id
    private Long roleId;

    // 创建时间
    private LocalDateTime createTime;
}

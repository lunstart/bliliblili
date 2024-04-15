package com.bliliblili.domain.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/9 20:30
 * @ 注释  权限控制角色与元素操作关联
 */
@Data
public class AuthRoleElementOperation {
    // 主键id
    private Long id;

    // 权限控制角色id
    private Long roleId;

    // 元素操作id
    private Long elementOperationId;

    // 创建时间
    private LocalDateTime createTime;

    //权限控制页面元素操作
    private AuthElementOperation authElementOperation;
}

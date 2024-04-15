package com.bliliblili.domain.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/9 20:33
 * @ 注释 权限控制角色页面菜单
 */
@Data
public class AuthRoleMenu {
    //主键
    private Long id;

    //角色id
    private Long roleId;

    //页面菜单id
    private Long menuId;

    //创建时间
    private LocalDateTime createTime;

    //权限控制页面访问
    private AuthMenu authMenu;
}

package com.bliliblili.domain.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/9 20:27
 * @ 注释 权限控制页面访问
 */
@Data
public class AuthMenu {

    //主键
    private Long id;

    //菜单项目名称
    private String name;

    //唯一编码
    private String code;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

}

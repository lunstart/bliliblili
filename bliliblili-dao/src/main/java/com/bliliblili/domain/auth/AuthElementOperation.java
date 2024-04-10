package com.bliliblili.domain.auth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/9 20:21
 * @ 注释 权限控制页面元素操作
 */
@Data
public class AuthElementOperation {
    // 主键
    private Long id;

    // 页面元素名称
    private String elementName;

    // 页面元素唯一编码
    private String elementCode;

    //操作类型:0可操作 1可见
    private String operationType;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;



}

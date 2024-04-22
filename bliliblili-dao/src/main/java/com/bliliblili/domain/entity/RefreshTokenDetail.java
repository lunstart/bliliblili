package com.bliliblili.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/20 18:14
 * @ 注释
 */
@Data
public class RefreshTokenDetail {
    // 主键
    private Long id;

    // 刷新token
    private String refreshToken;

    //用户id
    private Long userId;

    // 创建时间
    private LocalDateTime createTime;
}

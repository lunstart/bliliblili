package com.bliliblili.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/21 21:26
 * @ 注释
 */
@Data
@Builder
public class UserCoin {
    // 主键id
    private Long id;

    // 用户id
    private Long userId;

    //硬币数量
    private Integer coin;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;
}

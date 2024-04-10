package com.bliliblili.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/24 20:40
 * @ 注释
 */

@Data
public class FollowingGroup {
    //主键id
    private Long id;

    //用户id
    private Long userId;

    //关注分组名称
    private String name;

    //关注分组类型：0特别关注，1悄悄关注，2默认关注，3用户自定义关注
    private String type;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

}

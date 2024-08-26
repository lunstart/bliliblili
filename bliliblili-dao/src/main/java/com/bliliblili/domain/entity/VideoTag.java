package com.bliliblili.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/14 22:39
 * @ 注释
 */
@Data
public class VideoTag {
    private Long id;

    // 视频id
    private Long videoId;

    // 标签id
    private Long tagId;

    // 创建时间
    private Date createTime;
}

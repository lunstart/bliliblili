package com.bliliblili.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/12 18:51
 * @ 注释
 */
@Data
public class File {
    private Long id;

    private String url;

    private String type;

    private String md5;

    private Date createTime;
}

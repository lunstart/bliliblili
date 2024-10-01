package com.bliliblili.domain.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Date;
import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/14 22:37
 * @ 注释
 */
@Data
@Document(indexName = "videos")
public class Video {
    @Id
    private Long id;

    //用户id
    @Field(type = FieldType.Long)
    private Long userId;

    //视频链接
    @Field(type = FieldType.Text)
    private String url;

    //封面
    private String thumbnail;

    //标题
    @Field(type = FieldType.Text)
    private String title;

    // 0自制 1转载
    private String type;

    //时长
    private String duration;

    //分区
    private String area;

    //标签列表
    //冗余字段，方便查询
    private List<VideoTag> videoTagList;

    //简介
    @Field(type = FieldType.Text)
    private String description;

    //创建时间
    @Field(type = FieldType.Date)
    private Date createTime;

    //更新时间
    @Field(type = FieldType.Date)
    private Date updateTime;
}

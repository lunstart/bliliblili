package com.bliliblili.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VideoCollectionDTO {

    private Long id;

    private Long videoId;

    private Long userId;

    private Long groupId;

    private Date createTime;

}

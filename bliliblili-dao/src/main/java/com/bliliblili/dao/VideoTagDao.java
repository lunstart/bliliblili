package com.bliliblili.dao;

import com.bliliblili.domain.entity.Tag;
import com.bliliblili.domain.entity.VideoTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ author ljw
 * @ data 2024/9/12 16:02
 */
@Mapper
public interface VideoTagDao {

    Long addVideoTag(Tag tag);

    void deleteVideoTag(Long tagId);

    Tag getTagByName(String name);

    Integer getVideoByTagId(Long tagId);

    List<Tag> getVideoTagListByVideoId(Long videoId);
}

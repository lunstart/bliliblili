package com.bliliblili.dao;

import com.bliliblili.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ author ljw
 * @ data 2024/9/12 16:02
 */
@Mapper
public interface VideoTagDao {

    Long addVideoTag(Tag tag);

    void deleteVideoTag(Long tagId);

    Tag getTagByName(String name);
}

package com.bliliblili.service.impl;

import com.bliliblili.dao.VideoDao;
import com.bliliblili.dao.VideoTagDao;
import com.bliliblili.domain.entity.Tag;
import com.bliliblili.service.VideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ author ljw
 * @ data 2024/9/12 16:00
 */

@Service
public class VideoTagServiceImpl implements VideoTagService {
    @Autowired
    private VideoTagDao videoTagDao;

    public Long addVideoTag(Tag tag) {
        Tag dbTag = videoTagDao.getTagByName(tag.getName());
        if (dbTag != null) {
            return dbTag.getId();
        }
        tag.setCreateTime(new Date());
        videoTagDao.addVideoTag(tag);
        return tag.getId();
    }


    public void deleteVideoTag(Long tagId) {
        Integer count = videoTagDao.getVideoByTagId(tagId);
        if(count != null){
            videoTagDao.deleteVideoTag(tagId);
        }
    }
}

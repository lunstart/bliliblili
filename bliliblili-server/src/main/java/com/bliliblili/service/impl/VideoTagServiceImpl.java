package com.bliliblili.service.impl;

import com.bliliblili.dao.VideoTagDao;
import com.bliliblili.domain.entity.Tag;
import com.bliliblili.service.VideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(dbTag!= null){
            return dbTag.getId();
        }
        videoTagDao.addVideoTag(tag);
        return tag.getId();
    }


    public void deleteVideoTag(Long tagId) {
        videoTagDao.deleteVideoTag(tagId);
    }
}

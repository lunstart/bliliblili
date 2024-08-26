package com.bliliblili.service.impl;

import com.bliliblili.dao.repository.VideoRepository;
import com.bliliblili.domain.entity.Video;
import com.bliliblili.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ author 星星草去哪了
 * @ data 2024/6/17 0:13
 * @ 注释
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void addVideo(Video video) {
        videoRepository.save(video);
    }

    public Video getVideos(String keyword) {
        return videoRepository.findByTitleLike(keyword);
    }
}

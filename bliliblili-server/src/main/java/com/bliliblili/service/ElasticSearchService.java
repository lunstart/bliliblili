package com.bliliblili.service;

import com.bliliblili.domain.entity.Video;

/**
 * @ author 星星草去哪了
 * @ data 2024/6/17 0:12
 * @ 注释
 */
public interface ElasticSearchService {
    void addVideo(Video video);

    Video getVideos(String keyword);
}

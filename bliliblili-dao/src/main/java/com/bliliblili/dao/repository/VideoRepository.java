package com.bliliblili.dao.repository;

import com.bliliblili.domain.entity.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ author 星星草去哪了
 * @ data 2024/6/17 0:19
 * @ 注释
 */
public interface VideoRepository extends ElasticsearchRepository<Video, Long> {
    Video findByTitleLike(String keyword);
}

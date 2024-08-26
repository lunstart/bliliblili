package com.bliliblili.service;

import com.bliliblili.domain.entity.Danmu;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/19 19:50
 * @ 注释
 */
public interface DanmuService {
    void addDanmu(Danmu danmu);


    void asyncAddDanmu(Danmu danmu);

    List<Danmu> getDanmus(Long videoId, String startTime, String endTime) throws Exception;

    void addDanmusToRedis(Danmu danmu);
}

package com.bliliblili.dao;

import com.bliliblili.domain.entity.Danmu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/19 19:53
 * @ 注释
 */
@Mapper
public interface DanmuDao {
    void addDanmu(Danmu danmu);

    List<Danmu> getDanmus(Map<String, Object> params);
}

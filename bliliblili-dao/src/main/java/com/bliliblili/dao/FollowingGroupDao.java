package com.bliliblili.dao;

import com.bliliblili.domain.entity.FollowingGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/24 23:10
 * @ 注释
 */

@Mapper
public interface FollowingGroupDao {
    FollowingGroup getGroupByType(String type);

    FollowingGroup getGroupById(Long id);

    List<FollowingGroup> getGroupsByUserId(Long userId);

    Long addFollowingGroup(FollowingGroup followingGroup);

    List<FollowingGroup> getUserFollowingGroups(Long userId);
}

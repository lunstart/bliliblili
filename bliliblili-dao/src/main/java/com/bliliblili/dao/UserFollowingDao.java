package com.bliliblili.dao;

import com.bliliblili.domain.entity.FollowingGroup;
import com.bliliblili.domain.entity.UserFollowing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/24 23:08
 * @ 注释
 */

@Mapper
public interface UserFollowingDao {
    Integer deleteUserFollowing(Long userId, Long followingId);

    Integer addUserFollowing(UserFollowing userFollowing);

    List<UserFollowing> getFollowings(Long userId);

    List<UserFollowing> getUserFans(Long userId);
}

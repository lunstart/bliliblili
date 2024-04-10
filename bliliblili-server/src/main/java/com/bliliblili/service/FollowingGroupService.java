package com.bliliblili.service;

import com.bliliblili.domain.entity.FollowingGroup;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/25 19:40
 * @ 注释
 */
public interface FollowingGroupService {
    /**
     * 通过分组名称获取分组
     *
     * @param type
     * @return
     */
    public FollowingGroup getGroupByType(String type);

    /**
     * 通过主键id获取分组
     *
     * @param id
     * @return
     */
    public FollowingGroup getGroupById(Long id);


    /**
     * 获取用户关注分组
     *
     * @param userId
     * @return
     */
    public List<FollowingGroup> getGroupsByUserId(Long userId);

    /**
     * 添加关注分组
     *
     * @param followingGroup
     * @return
     */
    void addFollowingGroup(FollowingGroup followingGroup);

    /**
     * 获取用户关注分组
     *
     * @param userId
     * @return
     */
    List<FollowingGroup> getUserFollowingGroups(Long userId);
}

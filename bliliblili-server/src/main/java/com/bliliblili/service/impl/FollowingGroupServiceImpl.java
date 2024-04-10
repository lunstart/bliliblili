package com.bliliblili.service.impl;

import com.bliliblili.dao.FollowingGroupDao;
import com.bliliblili.domain.entity.FollowingGroup;
import com.bliliblili.service.FollowingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/25 19:40
 * @ 注释
 */

@Service
public class FollowingGroupServiceImpl implements FollowingGroupService {
    @Autowired
    private FollowingGroupDao followingGroupDao;

    /**
     * 通过分组名称获取分组
     *
     * @param type
     * @return
     */
    public FollowingGroup getGroupByType(String type) {
        return followingGroupDao.getGroupByType(type);
    }

    /**
     * 通过主键id获取分组
     *
     * @param id
     * @return
     */
    public FollowingGroup getGroupById(Long id) {
        return followingGroupDao.getGroupById(id);
    }

    /**
     * @param userId
     * @return
     */
    public List<FollowingGroup> getGroupsByUserId(Long userId) {
        return followingGroupDao.getGroupsByUserId(userId);
    }

    /**
     * 添加关注分组
     *
     * @param followingGroup
     * @return
     */
    public void addFollowingGroup(FollowingGroup followingGroup) {
        followingGroupDao.addFollowingGroup(followingGroup);
    }

    /**
     * 获取用户关注分组
     *
     * @param userId
     * @return
     */
    public List<FollowingGroup> getUserFollowingGroups(Long userId) {
        return followingGroupDao.getUserFollowingGroups(userId);
    }
}

package com.bliliblili.service;

import com.bliliblili.domain.entity.FollowingGroup;
import com.bliliblili.domain.entity.UserInfo;
import com.bliliblili.domain.vo.FollowingGroupVO;
import com.bliliblili.domain.vo.FollowingVO;
import com.bliliblili.domain.vo.UserFollowingVO;
import com.bliliblili.domain.entity.UserFollowing;
import com.bliliblili.domain.vo.UserInfoVO;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/25 19:40
 * @ 注释
 */
public interface UserFollowingService {

    /**
     * 用户新增关注
     *
     * @param userFollowing
     */
    public void addUserFollowings(UserFollowing userFollowing);

    /**
     * 获取关注列表
     *
     * @param userId
     * @return
     */
    public List<FollowingGroupVO> getFollowings(Long userId);

    /**
     * 获取粉丝列表
     *
     * @param userId
     * @return
     */
    public List<FollowingVO> getUserFans(Long userId);

    /**
     * 添加关注分组
     *
     * @param followingGroup
     * @return
     */
    Long addUserFollowingGroups(FollowingGroup followingGroup);

    /**
     * 获取用户关注分组
     *
     * @param userId
     * @return
     */
    List<FollowingGroup> getUserFollowingGroups(Long userId);

    /**
     * 查询用户互关
     * @param list
     * @param userId
     * @return
     */
    List<UserInfo> checkFollowingStatus(List<UserInfo> list, Long userId);
}

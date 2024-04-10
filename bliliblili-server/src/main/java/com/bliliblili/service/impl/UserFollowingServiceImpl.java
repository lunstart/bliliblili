package com.bliliblili.service.impl;


import com.bliliblili.dao.UserFollowingDao;
import com.bliliblili.domain.entity.FollowingGroup;
import com.bliliblili.domain.entity.User;
import com.bliliblili.domain.entity.UserFollowing;
import com.bliliblili.domain.entity.UserInfo;
import com.bliliblili.domain.constant.UserConstant;
import com.bliliblili.domain.vo.FollowingGroupVO;
import com.bliliblili.domain.vo.FollowingVO;
import com.bliliblili.domain.vo.UserFollowingVO;
import com.bliliblili.domain.vo.UserInfoVO;
import com.bliliblili.service.FollowingGroupService;
import com.bliliblili.service.UserFollowingService;
import com.bliliblili.service.UserService;
import com.bliliblili.service.exception.ConditionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ author 星星草去哪了
 * @ data  2024/3/25 19:41
 * @ 注释
 */

@Service
public class UserFollowingServiceImpl implements UserFollowingService {
    @Autowired
    private UserFollowingDao userFollowingDao;

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private UserService userService;


    /**
     * 用户新增关注
     *
     * @param userFollowing
     */
    //事务回滚
    @Transactional
    public void addUserFollowings(UserFollowing userFollowing) {
        if (userFollowing.getFollowingId() == userFollowing.getUserId()) {
            throw new ConditionException("不能关注自己! ");
        }
        Long groupId = userFollowing.getGroupId();
        if (groupId == null) {
            FollowingGroup followingGroup = followingGroupService.getGroupByType(UserConstant.USER_FOLLOWING_GROUP_TYPE_DEFAULT);
            userFollowing.setGroupId(followingGroup.getId());
        } else {
            FollowingGroup followingGroup = followingGroupService.getGroupById(groupId);
            if (followingGroup == null) {
                throw new ConditionException("关注分组不存在! ");
            }
        }
        Long followingId = userFollowing.getFollowingId();
        User followingUser = userService.getUserByUserId(followingId);
        if (followingUser == null) {
            throw new ConditionException("关注用户不存在! ");
        }
        userFollowingDao.deleteUserFollowing(userFollowing.getUserId(), followingId);
        userFollowing.setCreateTime(LocalDateTime.now());
        userFollowingDao.addUserFollowing(userFollowing);
    }

    /**
     * 获取关注列表
     *
     * @param userId
     * @return
     */
    public List<FollowingGroupVO> getFollowings(Long userId) {
        //获取关注用户列表
        List<UserFollowing> list = userFollowingDao.getFollowings(userId);
        List<UserFollowingVO> followingList = new ArrayList<>();

        Set<Long> followingIdSet = list.stream().map(UserFollowing::getFollowingId).collect(Collectors.toSet());

        List<UserInfo> userInfoList = new ArrayList<>();
        //根据关注用的id查询关注用户的基本信息
        if (followingIdSet.size() > 0) {
            userInfoList = userService.getUserInfosByUserIds(followingIdSet);
        }

        //两个list列表进行合并
        for (UserFollowing userFollowing : list) {
            for (UserInfo userInfo : userInfoList) {
                if (userFollowing.getFollowingId() == userInfo.getUserId()) {
                    UserFollowingVO userFollowingVO = new UserFollowingVO();
                    userFollowingVO.setUserFollowing(userFollowing);
                    userFollowingVO.setUserInfo(userInfo);
                    followingList.add(userFollowingVO);
                }
            }
        }
        //将关注按用户关注分组进行分类
        //添加功能，通过用户id将关注用户的分组全部查出来
        List<FollowingGroup> groupList = followingGroupService.getGroupsByUserId(userId);
        List<FollowingGroupVO> groups = new ArrayList<>();
        for (FollowingGroup followingGroup : groupList) {
            FollowingGroupVO group = new FollowingGroupVO();
            group.setFollowingGroup(followingGroup);
            groups.add(group);
        }

        //全部分组
        FollowingGroupVO allGroup = new FollowingGroupVO();

        FollowingGroup followingGroup = new FollowingGroup();
        followingGroup.setName(UserConstant.USER_FOLLOWING_GROUP_ALL_NAME);
        allGroup.setFollowingGroup(followingGroup);

        allGroup.setUserInfoList(userInfoList);
        List<FollowingGroupVO> result = new ArrayList<>();
        result.add(allGroup);

        //添加其他分组
        for (FollowingGroupVO group : groups) {
            List<UserInfo> userInfo = new ArrayList<>();
            for (UserFollowingVO following : followingList) {
                if (group.getFollowingGroup().getId().equals(following.getUserFollowing().getGroupId())) {
                    userInfo.add(following.getUserInfo());
                }
            }
            group.setUserInfoList(userInfo);
            result.add(group);
        }
        return result;
    }

    /**
     * 获取粉丝列表
     *
     * @param userId
     * @return
     */
    public List<FollowingVO> getUserFans(Long userId) {
        //获取粉丝列表
        List<UserFollowing> fanList = userFollowingDao.getUserFans(userId);
        List<FollowingVO> fansList = new ArrayList<>();

        for (UserFollowing userFollowing : fanList) {
            FollowingVO followingVO = new FollowingVO();
            followingVO.setUserFollowing(userFollowing);
            fansList.add(followingVO);
        }

        Set<Long> fanIdSet = fanList.stream().map(UserFollowing::getUserId).collect(Collectors.toSet());
        List<UserInfo> userInfoList = new ArrayList<>();
        List<UserInfoVO> userInfoVOS = new ArrayList<>();
        if (fanIdSet.size() > 0) {
            userInfoList = userService.getUserInfosByUserIds(fanIdSet);
        }

        for (UserInfo userInfo : userInfoList) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setUserInfo(userInfo);
            userInfoVOS.add(userInfoVO);
        }

        //互关的初始化和粉丝的赋值
        //根据粉丝的用户id查询对应的粉丝基本信息
        List<UserFollowing> followingList = userFollowingDao.getFollowings(userId);
        List<UserFollowingVO> followingVOS = new ArrayList<>();
        //BeanUtils.copyProperties(followingList, followingVOS);
        for (UserFollowing follow : followingList) {
            UserFollowingVO followingVO = new UserFollowingVO();
            followingVO.setUserFollowing(follow);
            followingVOS.add(followingVO);
        }

        //统一定义不关注
        for (FollowingVO fan : fansList) {
            for (UserInfoVO userInfo : userInfoVOS) {
                if (fan.getUserFollowing().getUserId().equals(userInfo.getUserInfo().getUserId())) {
                    userInfo.setFollowed(false);
                    fan.setUserInfo(userInfo.getUserInfo());
                }
            }
            //关注的用户和他的粉丝是否匹配
            //查询当前用户是否已经关注该粉丝了
            for (UserFollowingVO following : followingVOS) {
                if (following.getUserFollowing().getFollowingId().equals(fan.getUserInfo().getUserId())) {
                    fan.setFollowed(true);
                }
            }
        }
        return fansList;
    }

    /**
     * 添加关注分组
     *
     * @param followingGroup
     * @return
     */
    public Long addUserFollowingGroups(FollowingGroup followingGroup) {
        followingGroup.setCreateTime(LocalDateTime.now());
        followingGroup.setType(UserConstant.USER_FOLLOWING_GROUP_TYPE_USER);
        followingGroupService.addFollowingGroup(followingGroup);
        return followingGroup.getUserId();
    }

    /**
     * 获取用户关注分组
     *
     * @param userId
     * @return
     */
    public List<FollowingGroup> getUserFollowingGroups(Long userId) {
        return followingGroupService.getUserFollowingGroups(userId);
    }

    /**
     * 查询用户互关
     *
     * @param list
     * @param userId
     * @return
     */
    public List<UserInfo> checkFollowingStatus(List<UserInfo> list, Long userId) {
        List<UserFollowing> followingList = userFollowingDao.getFollowings(userId);
        for (UserInfo userInfo : list) {
            userInfo.setFollowed(false);
            for (UserFollowing following : followingList) {
                if (following.getUserId().equals(userInfo.getFollowed())) {
                    userInfo.setFollowed(true);
                }
            }
        }
        return list;
    }

}

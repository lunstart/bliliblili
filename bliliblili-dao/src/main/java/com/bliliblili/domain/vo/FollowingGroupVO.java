package com.bliliblili.domain.vo;

import com.bliliblili.domain.entity.FollowingGroup;
import com.bliliblili.domain.entity.UserInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/26 19:56
 * @ 注释
 */
@Data
public class FollowingGroupVO {
    //用户关注分组
    private FollowingGroup followingGroup;

    //用户信息列表
    private List<UserInfo> userInfoList;
}

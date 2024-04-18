package com.bliliblili.api;

import com.bliliblili.domain.entity.FollowingGroup;
import com.bliliblili.domain.entity.UserFollowing;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.domain.vo.FollowingGroupVO;
import com.bliliblili.domain.vo.FollowingVO;
import com.bliliblili.service.UserFollowingService;
import com.bliliblili.api.support.UserSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/25 20:03
 * @ 注释
 */

@RestController
@Slf4j
@Api(tags = "用户关注相关接口")
public class UserFollowingApi {
    @Autowired
    private UserFollowingService userFollowingService;

    //获取当前登录用户的信息
    @Autowired
    private UserSupport userSupport;


    /**
     * 添加关注
     *
     * @param userFollowing
     * @return
     */
    @PostMapping("user-followings")
    @ApiOperation("添加关注")
    public JsonResponse<String> addUserFollowings(@RequestBody UserFollowing userFollowing) {
        Long userId = userSupport.getCurrentUserId();
        log.info("添加关注：{}",userId,userFollowing.getFollowingId());
        userFollowing.setUserId(userId);
        userFollowingService.addUserFollowings(userFollowing);
        return JsonResponse.success();
    }

    /**
     * 获取关注列表
     *
     * @return
     */
    @GetMapping("/user-followings")
    @ApiOperation("获取关注列表")
    public JsonResponse<List<FollowingGroupVO>> getUserFollowings() {
        Long userId = userSupport.getCurrentUserId();
        log.info("获取关注列表：{}",userId);
        List<FollowingGroupVO> followings = userFollowingService.getFollowings(userId);
        return new JsonResponse<>(followings);
    }

    /**
     * 获取粉丝列表
     *
     * @return
     */
    @GetMapping("/user-fans")
    @ApiOperation("获取粉丝列表")
    public JsonResponse<List<FollowingVO>> getUserFans() {
        Long userId = userSupport.getCurrentUserId();
        log.info("获取粉丝列表:{}",userId);
        List<FollowingVO> userFans = userFollowingService.getUserFans(userId);
        return new JsonResponse<>(userFans);
    }

    /**
     * 添加关注分组
     *
     * @param followingGroup
     * @return
     */
    @PostMapping("/user-following-groups")
    @ApiOperation("添加关注分组")
    public JsonResponse<Long> addUserFollowingGroups(@RequestBody FollowingGroup followingGroup) {
        Long userId = userSupport.getCurrentUserId();
        log.info("添加关注分组:{}",userId);
        followingGroup.setUserId(userId);
        Long groupId = userFollowingService.addUserFollowingGroups(followingGroup);
        return new JsonResponse<>(groupId);
    }

    /**
     * 获取用户关注分组
     *
     * @return
     */
    @GetMapping("/user-following-groups")
    @ApiOperation("获取用户关注分组")
    public JsonResponse<List<FollowingGroup>> getUserFollowingGroups() {
        Long userId = userSupport.getCurrentUserId();
        log.info("获取用户关注分组:{}",userId);
        List<FollowingGroup> list = userFollowingService.getUserFollowingGroups(userId);
        return new JsonResponse<>(list);
    }
}

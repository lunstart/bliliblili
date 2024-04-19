package com.bliliblili.api;

import com.bliliblili.domain.annotation.ApiLimitedRole;
import com.bliliblili.domain.annotation.DataLimited;
import com.bliliblili.domain.constant.AuthRoleConstant;
import com.bliliblili.domain.entity.UserMoments;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.UserMomentService;
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
 * @ data 2024/4/6 16:30
 * @ 注释
 */
@RestController
@Slf4j
@Api(tags = "用户动态相关接口")
public class UserMomentsApi {
    @Autowired
    private UserMomentService userMomentService;

    @Autowired
    private UserSupport userSupport;

    @ApiLimitedRole(limitedRoleCodeList = {AuthRoleConstant.ROLE_LV0})
    @DataLimited
    @PostMapping("/user-moments")
    @ApiOperation("新增动态")
    public JsonResponse<String> addUserMoments(@RequestBody UserMoments userMoments) throws Exception {
        Long userId = userSupport.getCurrentUserId();
        log.info("添加动态:{}", userId);
        userMoments.setUserId(userId);
        userMomentService.addUserMoments(userMoments);
        return JsonResponse.success();
    }

    @GetMapping("/user-subscribed-moments")
    @ApiOperation("获取用户订阅动态")
    public JsonResponse<List<UserMoments>> getUserSubscribedMoments() {
        Long userId = userSupport.getCurrentUserId();
        List<UserMoments> list = userMomentService.getUserSubscribedMoments(userId);
        log.info("获取用户订阅动态:{}", userId);
        return new JsonResponse<>(list);
    }
}

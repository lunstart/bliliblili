package com.bliliblili.api;

import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.domain.vo.UserAuthoritiesVO;
import com.bliliblili.service.UserAuthService;
import com.bliliblili.api.support.UserSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 11:04
 * @ 注释
 */
@RestController
@Slf4j
@Api (tags = "用户认证接口")
public class UserAuthApi {
    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/user-authorities")
    @ApiOperation("获取用户权限")
    public JsonResponse<UserAuthoritiesVO> getUserAuthorities() {
        Long userId = userSupport.getCurrentUserId();
        log.info("用户权限查询:{}", userId);
        UserAuthoritiesVO userAuthoritiesVO = userAuthService.getUserAuthorities(userId);
        return new JsonResponse<>(userAuthoritiesVO);
    }

}

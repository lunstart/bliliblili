package com.bliliblili.api;

import com.alibaba.fastjson.JSONObject;
import com.bliliblili.domain.entity.UserInfo;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.domain.entity.User;
import com.bliliblili.domain.dto.LoginUserDTO;
import com.bliliblili.domain.dto.RegisterUserDTO;
import com.bliliblili.domain.jsonresponse.PageResult;
import com.bliliblili.service.UserFollowingService;
import com.bliliblili.service.UserService;
import com.bliliblili.service.util.RSAUtil;
import com.bliliblili.api.support.UserSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/25 20:03
 * @ 注释
 */

@RestController
@Api(tags = "用户相关接口")
@Slf4j
public class UserApi {
    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserFollowingService userFollowingService;

    /**
     * 获取用户
     *
     * @return
     */
    @GetMapping("/users")
    @ApiOperation("获取用户")
    public JsonResponse<User> getUser() {
        Long userId = userSupport.getCurrentUserId();
        log.info("获取用户:{}", userId);
        User user = userService.getUserByUserId(userId);
        return new JsonResponse<>(user);
    }


    /**
     * 获取rsa加密密钥
     *
     * @return
     */
    @GetMapping("/rsa-pk")
    @ApiOperation("获取rsa公钥")
    public JsonResponse<String> getRsaPulicKey() {
        log.info("获取rsa公钥");
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    /**
     * 用户注册
     *
     * @param registerUserDTO
     * @return
     */
    @PostMapping("/users")
    @ApiOperation("用户注册")
    public JsonResponse<String> addUser(@RequestBody RegisterUserDTO registerUserDTO) {
        log.info("用户注册:{}", registerUserDTO);
        userService.addUser(registerUserDTO);
        return JsonResponse.success();
    }

    /**
     * 用户登录
     *
     * @param loginUserDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/user-token")
    @ApiOperation("用户登录")
    public JsonResponse<String> login(@RequestBody LoginUserDTO loginUserDTO) throws Exception {
        log.info("用户登录:{}", loginUserDTO.getPhone() == null ? loginUserDTO.getEmail() : loginUserDTO.getPhone());
        String token = userService.login(loginUserDTO);
        return new JsonResponse<>(token);
    }

    /**
     * 用户信息更新
     *
     * @param user
     * @return
     */
    @PostMapping("user")
    @ApiOperation("用户信息更新")
    public JsonResponse<String> updateUser(@RequestBody User user) throws Exception {
        Long userId = user.getId();
        //Long userId = userSupport.getCurrentUserId();
        log.info("用户信息更新：{}", userId);
        user.setId(userId);
        userService.updateUser(user);
        return JsonResponse.success();
    }

    /**
     * 用户其他相关信息更新
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/user-infos")
    @ApiOperation("用户其他相关信息更新")
    public JsonResponse<String> updateUserInfos(@RequestBody UserInfo userInfo) {
        Long userId = userInfo.getUserId();
        //Long userId = userSupport.getCurrentUserId();
        log.info("用户其他相关信息更新：{}", userId);
        userInfo.setUserId(userId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }

    /**
     * 分页查询用户列表
     *
     * @return
     */
    @GetMapping("/user-infos")
    @ApiOperation("获取用户列表")
    public JsonResponse<PageResult<UserInfo>> getUserInfo(@RequestParam Integer num, @RequestParam Integer size, String nick) {
        Long userId = userSupport.getCurrentUserId();
        log.info("获取用户列表：{}");
        JSONObject params = new JSONObject();
        params.put("num", num);
        params.put("size", size);
        params.put("nick", nick);
        params.put("userId", userId);
        PageResult<UserInfo> result = userService.pageListUserInfos(params);
        if (result.getTotal() > 0) {
            List<UserInfo> checkedUserInfoList = userFollowingService.checkFollowingStatus(result.getList(), userId);
            result.setList(checkedUserInfoList);
        }
        return new JsonResponse<>(result);
    }

    /**
     * 用户登录用户双token
     *
     * @param loginUserDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/user-dts")
    @ApiOperation("用户登录用户双token")
    public JsonResponse<Map<String, Object>> loginForDts(@RequestBody LoginUserDTO loginUserDTO) throws Exception {
        log.info("用户登录:{}", loginUserDTO.getPhone() != null ? loginUserDTO.getPhone() : loginUserDTO.getEmail());
        Map<String, Object> map = userService.loginForDts(loginUserDTO);
        return new JsonResponse<>(map);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @DeleteMapping("/refresh-tokens")
    @ApiOperation("退出登录")
    public JsonResponse<String> logout(HttpServletRequest request) {
        Long userId = userSupport.getCurrentUserId();
        log.info("用户退出:{}", userId);
        String refreshToken = request.getHeader("refreshToken");
        userService.logout(refreshToken, userId);
        return JsonResponse.success();
    }

    @PostMapping("/access-tokens")
    @ApiOperation("刷新token")
    public JsonResponse<String> refreshAccessToken(HttpServletRequest request) throws Exception {
        log.info("用户token刷新:{}");
        String refreshToken = request.getHeader("refreshToken");
        String AccessToken = userService.refreshAccessToken(refreshToken);
        return new JsonResponse<>(AccessToken);
    }
}


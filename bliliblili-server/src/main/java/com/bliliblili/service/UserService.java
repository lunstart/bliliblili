package com.bliliblili.service;

import com.alibaba.fastjson.JSONObject;
import com.bliliblili.domain.entity.User;
import com.bliliblili.domain.dto.LoginUserDTO;
import com.bliliblili.domain.dto.RegisterUserDTO;
import com.bliliblili.domain.entity.UserInfo;
import com.bliliblili.domain.jsonresponse.PageResult;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface UserService {
    /**
     * 用户注册
     *
     * @param registerUserDTO
     */
    public void addUser(RegisterUserDTO registerUserDTO);

    /**
     * 获取用户
     *
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);

    /**
     * 用户登录
     *
     * @return
     */
    public String login(LoginUserDTO user) throws Exception;

    /**
     * 通过用户id获取用户
     *
     * @param userId
     * @return
     */
    User getUserByUserId(Long userId);

    /**
     * 用户其他相关信息更新
     *
     * @param userInfo
     */
    void updateUserInfos(UserInfo userInfo);

    /**
     * 用户信息跟新
     *
     * @param user
     * @throws Exception
     */
    void updateUser(User user) throws Exception;

    /**
     * 获取用户列表
     *
     * @param userIdList
     * @return
     */
    List<UserInfo> getUserInfosByUserIds(Set<Long> userIdList);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     * 分页查询用户
     *
     * @param params
     * @return
     */
    PageResult<UserInfo> pageListUserInfos(JSONObject params);

    /**
     * 用户登录 双token
     *
     * @param user
     * @return
     */
    Map<String, Object> loginForDts(LoginUserDTO user) throws Exception;

    /**
     * 退出登录
     *
     * @param refreshToken
     * @param userId
     */
    void logout(String refreshToken, Long userId);

    /**
     * 刷新token
     *
     * @param refreshToken
     * @return
     */
    String refreshAccessToken(String refreshToken) throws Exception;
}

package com.bliliblili.dao;

import com.alibaba.fastjson.JSONObject;
import com.bliliblili.domain.entity.RefreshTokenDetail;
import com.bliliblili.domain.entity.User;
import com.bliliblili.domain.entity.UserInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserDao {

    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserById(Long userId);

    UserInfo getUserInfoByUserId(Long userId);

    Integer updateUserInfos(UserInfo userInfo);

    void updateUsers(User user);

    User getUserByAccount(String account);

    List<UserInfo> getUserinfosByUserIds(Set<Long> userIdList);

    Integer pageCountUsrInfos(Map<String, Object> params);

    List<UserInfo> pageListUserInfos(Map<String, Object> params);

    Integer deleteRefreshToken(String refreshToken, Long userId);

    Integer addRefreshToken(String refreshToken, Long userId, LocalDateTime createTime);

    RefreshTokenDetail getRefreshToken(String refreshToken);
}

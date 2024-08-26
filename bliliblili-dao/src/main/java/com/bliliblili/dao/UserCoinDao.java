package com.bliliblili.dao;

import com.bliliblili.domain.entity.UserCoin;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/17 22:39
 * @ 注释
 */
@Mapper
public interface UserCoinDao {

    Integer getUserCoinsAmount(Long userId);

    Integer updateUserCoinAmount(Long userId, Integer amount, Date updateTime);

    Integer loginAddUserCoin(Long userId, Integer amount, Date updateTime);

    Integer addUserCoin(UserCoin userCoin);
}

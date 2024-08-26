package com.bliliblili.service;

import com.bliliblili.domain.entity.UserCoin;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/20 15:31
 * @ 注释
 */
public interface UserCoinService {
    Integer getUserCoinsAmount(Long userId);

    void updateUserCoinsAmount(Long userId, Integer amount);

    void loginAddUserCoin(Long userId, Integer amount);

    void addUserCoin(UserCoin userCoin);
}

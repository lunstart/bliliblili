package com.bliliblili.service.impl;

;
import com.bliliblili.dao.UserCoinDao;
import com.bliliblili.domain.entity.UserCoin;
import com.bliliblili.service.UserCoinService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCoinServiceImpl implements UserCoinService {

    @Autowired
    private UserCoinDao userCoinDao;


    public Integer getUserCoinsAmount(Long userId) {
        return userCoinDao.getUserCoinsAmount(userId);
    }

    public void updateUserCoinsAmount(Long userId, Integer amount) {
        Date updateTime = new Date();
        userCoinDao.updateUserCoinAmount(userId, amount, updateTime);
    }


    public void loginAddUserCoin(Long userId, Integer amount) {
        Date now = new Date();
        userCoinDao.loginAddUserCoin(userId, amount, now);
    }


    public void addUserCoin(UserCoin userCoin) {
        userCoinDao.addUserCoin(userCoin);
    }
}

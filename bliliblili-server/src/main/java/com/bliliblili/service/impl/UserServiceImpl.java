package com.bliliblili.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bliliblili.domain.entity.User;
import com.bliliblili.domain.jsonresponse.PageResult;
import com.bliliblili.domain.constant.UserConstant;
import com.bliliblili.domain.entity.UserInfo;
import com.bliliblili.domain.dto.LoginUserDTO;
import com.bliliblili.domain.dto.RegisterUserDTO;
import com.bliliblili.service.UserService;
import com.bliliblili.service.util.MD5Util;
import com.bliliblili.service.util.RSAUtil;
import com.bliliblili.service.util.TokenUtil;
import com.mysql.cj.util.StringUtils;
import com.bliliblili.dao.UserDao;
import com.bliliblili.service.exception.ConditionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/22 21:40
 * @ 注释
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 添加用户
     *
     * @param registerUserDTO
     */
    public void addUser(RegisterUserDTO registerUserDTO) {
        //创建新的user,并将dto赋值给user
        User user = new User();
        BeanUtils.copyProperties(registerUserDTO,user);

        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号码不能为空!");
        }
        User dbUser = getUserByPhone(phone);
        if (dbUser != null) {
            throw new ConditionException("用户已注册!");
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败!");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setPassword(md5Password);
        user.setCreateTime(LocalDateTime.now());
        user.setSalt(salt);
        userDao.addUser(user);

        user = userDao.getUserByPhone(phone);
        //添加用户信息
        UserInfo userInfo = new UserInfo();
        //System.out.println(user.getId());
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        //userInfo.setAvatar();
        userInfo.setCreateTime(user.getCreateTime());
        userDao.addUserInfo(userInfo);
    }

    /**
     * 获取用户电话
     *
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    /**
     * 用户登录
     *
     * @return
     */
    public String login(LoginUserDTO user) throws Exception {
        String email = user.getEmail() == null ? "" : user.getEmail();
        String phone = user.getPhone() == null ? "" : user.getPhone();
        if (StringUtils.isNullOrEmpty(phone) && StringUtils.isNullOrEmpty(email)) {
            throw new ConditionException("账号不能为空!");
        }
        String account = email + phone;
        //User dbUser = userDao.getUserByPhone(phone);
        User dbUser = userDao.getUserByAccount(account);
        if (dbUser == null) {
            throw new ConditionException("用户未注册!");
        }
        //rsa加密密码
        String password = user.getPassword();
        //System.out.println(password);
        //解密密码
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败!");
        }
        //进行md5加密
        String salt = dbUser.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        if(!md5Password.equals(dbUser.getPassword())){
            throw new ConditionException("密码错误!");
        }
        return TokenUtil.generateToken(dbUser.getId());
    }

    /**
     *获取用户
     * @param userId
     * @return
     */
    public User getUserByUserId(Long userId){
        User user = userDao.getUserById(userId);
        UserInfo userInfo = userDao.getUserInfoByUserId(userId);
        user.setUserInfo(userInfo);
        return user;
    }

    /**
     * 用户其他相关信息更新
     * @param userInfo
     */
    public void updateUserInfos(UserInfo userInfo) {
        userInfo.setUpdateTime(LocalDateTime.now());
        userDao.updateUserInfos(userInfo);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(User user) throws Exception {
        User dbUser = userDao.getUserById(user.getId());
        if(dbUser == null){
            throw new ConditionException("用户不存在");
        }
        if(!StringUtils.isNullOrEmpty(user.getPassword())){
            String rsaPassword = RSAUtil.decrypt(user.getPassword());
            String md5Password = MD5Util.sign(rsaPassword, dbUser.getSalt(), "UTF-8");
            user.setPassword(md5Password);
        }
        user.setUpdateTime(LocalDateTime.now());
        userDao.updateUsers(user);
    }

    /**
     *
     * @param userIdList
     * @return
     */
    public List<UserInfo> getUserInfosByUserIds(Set<Long> userIdList) {
        /*List<UserInfo> userInfoList = new ArrayList<>();
        for(Long userId : followingIdSets){
            UserInfo userInfo = userDao.getUserInfoByUserId(userId);
            userInfoList.add(userInfo);
        }*/
        return userDao.getUserinfosByUserIds(userIdList);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public UserInfo getUserInfoByUserId(Long userId) {
        return userDao.getUserInfoByUserId(userId);
    }

    /**
     * 获取用户列表
     * @param params
     * @return
     */
    public PageResult<UserInfo> pageListUserInfos(JSONObject params) {
        Integer num = params.getInteger("num");
        Integer size = params.getInteger("size");
        params.put("start",(num - 1) * size);
        params.put("limit",size);
        Integer total = userDao.pageCountUsrInfos(params);
        List<UserInfo> list = new ArrayList<>();
        if(total > 0){
            list = userDao.pageListUserInfos(params);
        }
        return new PageResult<>(total,list);
    }
}

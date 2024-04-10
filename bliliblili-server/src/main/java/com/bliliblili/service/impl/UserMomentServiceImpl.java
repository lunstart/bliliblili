package com.bliliblili.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bliliblili.dao.UserMomentsDao;
import com.bliliblili.domain.constant.UserMomentsConstant;
import com.bliliblili.domain.entity.UserMoments;
import com.bliliblili.service.UserMomentService;

import com.bliliblili.service.util.RocketMQUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/6 16:33
 * @ 注释
 */
@Service
public class UserMomentServiceImpl implements UserMomentService {
    @Autowired
    private UserMomentsDao userMomentsDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 添加动态
     *
     * @param userMoments
     */
    public void addUserMoments(UserMoments userMoments) throws Exception {
        userMoments.setCreateTime(LocalDateTime.now());
        userMomentsDao.addUserMoments(userMoments);
        DefaultMQProducer producer = (DefaultMQProducer) applicationContext.getBean("momentsProducer");
        Message msg = new Message(UserMomentsConstant.TOPIC_MOMENTS, JSONObject.toJSONString(userMoments).getBytes(StandardCharsets.UTF_8));
        RocketMQUtil.syncSendMsg(producer, msg);
    }

    /**
     * 获取用户订阅动态
     *
     * @param userId
     * @return
     */
    public List<UserMoments> getUserSubscribedMoments(Long userId) {
        String key = "subscribedList" + userId;
        String listStr = redisTemplate.opsForValue().get(key);
        return JSONArray.parseArray(listStr,UserMoments.class);
    }
}

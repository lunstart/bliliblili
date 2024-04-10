package com.bliliblili.service.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bliliblili.domain.constant.UserMomentsConstant;
import com.bliliblili.domain.entity.UserMoments;
import com.bliliblili.domain.vo.FollowingVO;
import com.bliliblili.service.UserFollowingService;
import com.mysql.cj.util.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/3 10:41
 * @ 注释
 */
@Configuration
public class RocketMQConfig {
    @Value("${rocket.name.server.address}")
    private String nameServerAddr;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserFollowingService userFollowingService;

    @Bean("momentsProducer")
    public DefaultMQProducer momentsProducer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(UserMomentsConstant.GROUP_MOMENTS);
        producer.setNamesrvAddr(nameServerAddr);
        producer.start();
        return producer;
    }

    /**
     * 创建并初始化一个用于消费用户动态消息的DefaultMQPushConsumer实例。
     *
     * @return DefaultMQPushConsumer 已经配置并启动的消费者实例
     * @throws Exception 如果在配置或启动消费者时发生错误，则抛出异常
     */
    //@Bean("momentsConsumer")
    public DefaultMQPushConsumer momentsConsumer() throws Exception {
        // 创建一个消费用户动态消息的消费者实例
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(UserMomentsConstant.TOPIC_DANMUS);
        consumer.setNamesrvAddr(nameServerAddr); // 设置NameServer地址
        consumer.subscribe(UserMomentsConstant.TOPIC_MOMENTS, "*"); // 订阅用户动态主题的所有消息

        // 添加消息监听器，用于处理接收到的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt msg = msgs.get(0);
                //判断消息是否为空
                if (msg == null) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                // 将消息体转换为Java对象
                String bodyStr = new String(msg.getBody());
                UserMoments userMoments = JSONObject.toJavaObject(JSONObject.parseObject(bodyStr), UserMoments.class);
                Long userId = userMoments.getUserId();

                List<FollowingVO> userFans = userFollowingService.getUserFans(userId);
                for (FollowingVO fan : userFans) {
                    String key = "subscribedList" + fan.getUserInfo().getUserId();
                    String subscribedListStr = redisTemplate.opsForValue().get(key);
                    List<UserMoments> subscribedList;
                    if (StringUtils.isNullOrEmpty(subscribedListStr)) {
                        subscribedList = new ArrayList<>();
                    } else {
                        subscribedList = JSONArray.parseArray(subscribedListStr, UserMoments.class);
                    }
                    subscribedList.add(userMoments);
                    redisTemplate.opsForValue().set(key, toJSONString(subscribedList));
                }
                // 消费成功状态返回
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start(); // 启动消费者
        return consumer; // 返回已启动的消费者实例
    }

}

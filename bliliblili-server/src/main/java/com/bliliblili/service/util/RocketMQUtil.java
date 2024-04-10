package com.bliliblili.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/6 15:40
 * @ 注释
 */
@Slf4j
public class RocketMQUtil {
    /**
     * 同步发送消息的函数。
     *
     * @param producer 消息生产者，用于发送消息的客户端实例。
     * @param msg      要发送的消息对象。
     * @throws Exception 如果发送过程中出现错误，则抛出异常。
     */
    public static void syncSendMsg(DefaultMQProducer producer, Message msg) throws Exception {
        // 发送消息并获取发送结果
        SendResult result = producer.send(msg);
        // 打印发送结果
        log.info("生产者:{}",result);
    }

    /**
     * 异步发送消息的函数。
     *
     * @param producer
     * @param msg
     * @throws Exception
     */
    public static void asynSendMsg(DefaultMQProducer producer, Message msg) throws Exception {
        int messageCount = 2;
        CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    log.info("发送消息id:{}",sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    log.info("发送消息时发生异常",e);
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
    }
}

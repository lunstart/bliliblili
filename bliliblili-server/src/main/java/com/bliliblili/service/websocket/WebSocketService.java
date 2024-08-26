package com.bliliblili.service.websocket;

import com.alibaba.fastjson.JSONObject;
import com.bliliblili.domain.entity.Danmu;
import com.bliliblili.service.DanmuService;
import com.bliliblili.service.util.TokenUtil;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/19 10:12
 * @ 注释
 */
@Component
@ServerEndpoint("/imserver/{token}")
@Slf4j
public class WebSocketService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 在线人数
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    public static final ConcurrentHashMap<String, WebSocketService> WEBSOCKET_MAP = new ConcurrentHashMap<>();

    private Session session;

    private String sessionId;

    private Long userId;

    private static ApplicationContext APPLICATION_CONTEXT;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketService.APPLICATION_CONTEXT = applicationContext;
    }

    @OnOpen
    public void openConnection(Session session, @PathParam("token") String token) {
        try {
            this.userId = TokenUtil.verifyToken(token);
        } catch (Exception e) {
        }
        this.sessionId = session.getId();
        this.session = session;
        if (WEBSOCKET_MAP.containsKey(sessionId)) {
            WEBSOCKET_MAP.remove(sessionId);
            WEBSOCKET_MAP.put(sessionId, this);
        } else {
            WEBSOCKET_MAP.put(sessionId, this);
            ONLINE_COUNT.getAndIncrement();
            logger.info("用户连接成功：" + sessionId + ". 当前在线人数为：" + ONLINE_COUNT.get());
        }

        try {
            this.sendMessage("0");
        } catch (Exception e) {
            logger.info("连接异常");
        }
    }

    @OnClose
    public void closeConnection() {
        if (WEBSOCKET_MAP.containsKey(sessionId)) {
            WEBSOCKET_MAP.remove(sessionId);
            ONLINE_COUNT.getAndDecrement();

        }
        logger.info("用户连接断开：" + sessionId + ". 当前在线人数为：" + ONLINE_COUNT.get());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("用户信息：{} 报文：{}", sessionId, message);
        if (StringUtils.isNullOrEmpty(message)) {
            try {
                //群发消息
                for (Map.Entry<String, WebSocketService> entry : WEBSOCKET_MAP.entrySet()) {
                    WebSocketService webSocketService = entry.getValue();
                    if (webSocketService.session.isOpen()) {
                        webSocketService.sendMessage(message);
                    }
                }
                if (this.userId != null) {
                    //保存弹幕-数据库
                    Danmu danmu = JSONObject.parseObject(message, Danmu.class);
                    danmu.setUserId(userId);
                    danmu.setCreateTime(new Date());
                    DanmuService danmuService = (DanmuService) APPLICATION_CONTEXT.getBean(DanmuService.class);
                    danmuService.addDanmu(danmu);
                    //保存弹幕-redis
                    danmuService.addDanmusToRedis(danmu);
                }
            } catch (Exception e) {
                log.info("弹幕异常");
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Throwable error) {

    }

    public void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }

}

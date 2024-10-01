package com.bliliblili.api.support;

import com.bliliblili.service.exception.ConditionException;
import com.bliliblili.service.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/25 20:03
 * @ 注释
 */

@Component
@Slf4j
public class UserSupport {
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //String token = requestAttributes.getRequest().getHeader("token");
        String token = requestAttributes.getRequest().getHeader("token");
        for(int i = 0;i < 10;i++){
            if(token != null) break;
            token =requestAttributes.getRequest().getHeader("token" + i);
        }
        if(token == null) throw  new ConditionException("非法用户! ");
        //TODO
        //测试环境配置
        Long userId = TokenUtil.verifyToken(token);
        if (userId <= 0) {
            throw new ConditionException("非法用户!");
        }
        return userId;
    }
}

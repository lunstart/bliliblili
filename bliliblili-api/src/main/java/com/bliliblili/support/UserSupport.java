package com.bliliblili.support;

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
        String token = requestAttributes.getRequest().getHeader("token");
        String token0 = requestAttributes.getRequest().getHeader("token0");
        token = token == null ? token0 : token;
        //TODO
        //测试环境配置
        Long userId = TokenUtil.verifyToken(token);
        if (userId <= 0) {
            throw new ConditionException("非法用户!");
        }
        return userId;
    }
}

package com.camel.redis.utils;

import com.camel.redis.entity.RedisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;

public class SessionContextUtils {
    private final static SessionContextUtils INSTANCE = new SessionContextUtils();

    public SessionContextUtils() {
    }

    public static SessionContextUtils getInstance(){return INSTANCE;}

    public RedisUser currentUser(RedisTemplate redisTemplate){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        byte[] cu = (byte[]) operations.get("CURRENT_USER");
        RedisUser sysUser = (RedisUser) SerizlizeUtil.unserizlize(cu);
        return sysUser;
    }
}

package com.camel.redis.exceptions;

/** @author baily */
public class RedisServiceNotFoundException extends RuntimeException {
    public RedisServiceNotFoundException() {
        super("未发现可用的Redis服务器！请检查");
    }
}

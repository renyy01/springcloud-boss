package com.camel.core.config;

import org.springframework.web.cors.CorsConfiguration;

/** @author baily */
public class MyCorsConfiguration {
    public static CorsConfiguration buildConfig() {
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        // 1允许任何域名使用
        config.addAllowedOrigin("*");
        // 2允许任何头
        config.addAllowedHeader("*");
        // 3允许任何方法（post、get等）
        config.addAllowedMethod("*");
        return config;
    }
}

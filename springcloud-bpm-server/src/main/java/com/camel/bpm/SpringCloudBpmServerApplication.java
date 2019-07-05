package com.camel.bpm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 *                 ___====-_  _-====___
 *           _--^^^#####//      \\#####^^^--_
 *        _-^##########// (    ) \\##########^-_
 *       -############//  |\^^/|  \\############-
 *     _/############//   (@::@)   \\############\_
 *    /#############((     \\//     ))#############\
 *   -###############\\    (oo)    //###############-
 *  -#################\\  / VV \  //#################-
 * -###################\\/      \//###################-
 *_#/|##########/\######(   /\   )######/\##########|\#_
 *|/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
 *`  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
 *   `   `  `      `   / | |  | | \   '      '  '   '
 *                    (  | |  | |  )
 *                   __\ | |  | | /__
 *                  (vvv(VVV)(VVV)vvv)
 * <BPM服务>
 * @author baily
 * @since 1.0
 * @date 2019/7/5
 **/
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.camel.bpm.mapper")
public class SpringCloudBpmServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudBpmServerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LoggerFactory.getLogger(this.getClass()).info("BPM系统启动成功... ...");
    }
}

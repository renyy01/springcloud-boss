package com.camel.auth.controller;

import com.camel.auth.service.MyUserDetailService;
import com.camel.core.entity.Result;
import com.camel.core.enumeration.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
 *
 * @author baily
 * @since 1.0
 * @date 2019/7/4
 **/
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/me")
    public Principal user(Principal member) {
        return member;
    }

    @DeleteMapping(value = "/exit")
    public Result revokeToken(String access_token) {
        Result result = new Result();
        if (consumerTokenServices.revokeToken(access_token)) {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage("注销成功");
        } else {
            result.setCode(ResultCode.FAILED.getCode());
            result.setMessage("注销失败");
        }
        return result;
    }
}

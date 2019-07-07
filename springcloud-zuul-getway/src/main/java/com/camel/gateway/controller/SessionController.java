package com.camel.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/me")
    public Principal me(HttpSession session, Principal principal) {
        return principal;
    }
}

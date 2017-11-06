/*
 * Copyright (c) 2013-2015, 成都中联信通科技股份有限公司
 */
package com.suimi.security.oauth2server.mvc;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suimi
 * @date 2017-10-24
 */
@RestController
public class UserController {

    @GetMapping("/users/test")
    public String test() {
        return "123";
    }

    @GetMapping("/users/user")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }


}

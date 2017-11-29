/*
 * Copyright (c) 2013-2015,  suimi
 */
package com.suimi.security.oauth2client.mvc;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suimi
 * @date 2017-10-24
 */
@RestController
public class UserController {


    @GetMapping("/")
    public String home() {
        return "welcome";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }


}

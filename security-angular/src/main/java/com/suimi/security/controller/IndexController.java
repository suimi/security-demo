/*
 * Copyright (c) 2013-2015,  suimi
 */
package com.suimi.security.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suimi
 * @date 2017-11-07
 */
@RestController
public class IndexController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}

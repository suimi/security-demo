/*
 * Copyright (c) 2013-2015,  suimi
 */
package com.suimi.security.oauth2server.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author suimi
 * @date 2017-10-23
 * @deprecated see {@link com.suimi.security.oauth2server.mvc.WebPageConfig}
 */
@Deprecated
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

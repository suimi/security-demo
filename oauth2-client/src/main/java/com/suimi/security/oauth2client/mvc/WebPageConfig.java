/*
 * Copyright (c) 2013-2015,  suimi
 */
package com.suimi.security.oauth2client.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author suimi
 * @date 2017-10-24
 */
@Configuration
public class WebPageConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/oauthLogin", "http://localhost:9001/oauth/authorize?response_type=token&scope=user&client_id=user_client&state=09876999");
    }
}

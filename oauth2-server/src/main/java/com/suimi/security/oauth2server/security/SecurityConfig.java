/*
 * Copyright (c) 2013-2015, 成都中联信通科技股份有限公司
 */
package com.suimi.security.oauth2server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author suimi
 * @date 2017-10-23
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
            .and().withUser("admin").password("admin").roles("USER", "ADMIN", "READER", "WRITER")
            .and().withUser("manage").password("manage").roles("MANAGE")
            .and().withUser("audit").password("audit").roles("USER", "ADMIN", "READER");
        // @formatter:on
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.httpBasic().disable()
            .formLogin().loginPage("/login").permitAll().and()
            // 只对Basic认证做处理，如果不配置，与资源服务在同一应用情况下，资源oauth2认证会被忽略
            .requestMatcher(request -> {
                String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
                return (auth != null && auth.startsWith("Basic"));
            })
            .authorizeRequests()
                .antMatchers("/login","/oauthLogin","/","/css/**", "/js/**", "/fonts/**","**.ico").permitAll()
                .antMatchers("/oauth/authorize","/oauth/confirm_access").permitAll()
                .antMatchers("/h2-console/**").hasRole("ADMIN")
    //            .antMatchers("/users/**").access("#oauth2.hasScope('user') or hasRole('USER')")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated();
//            .and().csrf().disable();
        // @formatter:on
    }
}

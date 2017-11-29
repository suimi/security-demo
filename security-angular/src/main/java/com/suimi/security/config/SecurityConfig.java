/*
 * Copyright (c) 2013-2015,  suimi
 */
package com.suimi.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

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
            .formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll().and()
            // 只对Basic认证做处理，如果不配置，与资源服务在同一应用情况下，资源oauth2认证会被忽略
            .authorizeRequests()
                .antMatchers("/login","/login.html","/css/**", "/js/**","/img/**", "/fonts/**","**.ico").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .csrf().disable();
        // @formatter:on
    }
}

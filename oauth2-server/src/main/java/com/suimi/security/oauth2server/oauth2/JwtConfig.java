/*
 * Copyright (c) 2013-2015, 成都中联信通科技股份有限公司
 */
package com.suimi.security.oauth2server.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

/**
 * @author suimi
 * @date 2017-10-24
 */
@Configuration
public class JwtConfig {


    @Value("${config.jwt.signingKey}")
    private String signingKey;

    @Value("${config.jwt.publicKey:}" )
    private String publicKey;

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        if (signingKey.trim().startsWith("-----BEGIN")) {
            converter.setVerifierKey(publicKey);
        }else{
        }
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }
}

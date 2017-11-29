/*
 * Copyright (c) 2013-2015,  suimi
 */
package com.suimi.security.oauth2server.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author suimi
 * @date 2017-10-24
 */
@EnableAuthorizationServer
@Configuration
@Order(10)
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       /* // @formatter:off
        clients.inMemory()
               .withClient("acme")
                    .secret("acmesecret")
                    .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                    .redirectUris("http://localhost:9001")
                    .scopes("pwd")
                .and().withClient("acread")
                    .secret("acreadsecret")
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("user");
        // @formatter:on*/
        clients.withClientDetails(clientDetailsService());
    }

    @Bean
    public ClientDetailsService clientDetailsService() throws Exception {
//        ClientDetailsService clientDetailsService = new InMemoryClientDetailsService();
//        clientDetailsService.loadClientByClientId("clientId");
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        // @formatter:off
        builder.withClient("acme")
                   .secret("acmesecret")
                   .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                   .redirectUris("http://localhost:9001")
                   .scopes("pwd")
               .and()
               .withClient("acread")
                   .secret("acreadsecret")
                   .authorizedGrantTypes("authorization_code", "refresh_token")
                   .scopes("user")
                .and()
                .withClient("user_client")
                    .secret("usersecret")
                    .authorizedGrantTypes("implicit","authorization_code", "refresh_token")
                    .authorities("TRUSTED_CLIENT")
                    .scopes("user");
        // @formatter:on
        return builder.build();
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
                 .accessTokenConverter(tokenConverter);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
//        security.tokenKeyAccess("isAnonymous() || hasRole('ROLE_TRUSTED_CLIENT')");
        security.tokenKeyAccess("permitAll()");
    }
}

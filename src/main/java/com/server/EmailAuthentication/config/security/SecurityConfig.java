package com.server.EmailAuthentication.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                        ,"/v2/api-docs"
                        ,"/configuration/ui/**"
                        , "/swagger-resources/**"
                        , "/configuration/security/**"
                        , "/swagger-ui.html"
                        , "/webjars/**"
                        , "/swagger/**"
                );
    }
}
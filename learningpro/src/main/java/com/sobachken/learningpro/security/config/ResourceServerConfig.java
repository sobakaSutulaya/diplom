package com.sobachken.learningpro.security.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}

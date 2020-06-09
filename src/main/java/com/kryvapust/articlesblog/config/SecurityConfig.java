package com.kryvapust.articlesblog.config;

import com.kryvapust.articlesblog.model.enums.SecurityRoleName;
import com.kryvapust.articlesblog.security.jwt.JwtConfigurer;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private static final String USER_ENDPOINT = "/articlesBlog/user/**";
    private static final String CREATE_ARTICLE_ENDPOINT = "/articlesBlog/articles";
    private static final String LOGIN_ENDPOINT = "/articlesBlog/login";
    private static final String REGISTRATION_ENDPOINT = "/articlesBlog/account/registration";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, CREATE_ARTICLE_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.POST, CREATE_ARTICLE_ENDPOINT).hasRole(SecurityRoleName.USER.name())
                .antMatchers(LOGIN_ENDPOINT, REGISTRATION_ENDPOINT).permitAll()
                .antMatchers(USER_ENDPOINT).hasRole(SecurityRoleName.USER.name())
                .antMatchers("/articlesBlog/articles/**").hasRole(SecurityRoleName.USER.name())
                .anyRequest().permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));  // это значит что каждый post запрос проходит через jwt токен
    }
}



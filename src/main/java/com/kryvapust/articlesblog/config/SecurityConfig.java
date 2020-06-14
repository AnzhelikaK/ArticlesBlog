package com.kryvapust.articlesblog.config;

import com.kryvapust.articlesblog.model.enums.SecurityRoleName;
import com.kryvapust.articlesblog.security.jwt.JwtConfigurer;
import com.kryvapust.articlesblog.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@AllArgsConstructor

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String REGISTRATION_ENDPOINT = "/articlesBlog/account/registration";
    private static final String LOGIN_ENDPOINT = "/articlesBlog/login";
    private static final String VIEW_ALL_ARTICLES_ENDPOINT = "/articlesBlog/articles";
    private static final String VIEW_ALL_COMMENTS_ENDPOINT = "/articlesBlog/articles/*/comments";
    private static final String USER_ENDPOINT = "/articlesBlog/user/*";

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
                .antMatchers(REGISTRATION_ENDPOINT, LOGIN_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, VIEW_ALL_ARTICLES_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, VIEW_ALL_COMMENTS_ENDPOINT).permitAll()
                .antMatchers(USER_ENDPOINT).hasAnyRole(SecurityRoleName.USER.name(),SecurityRoleName.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));  // это значит что каждый post запрос проходит через jwt токен
    }
}

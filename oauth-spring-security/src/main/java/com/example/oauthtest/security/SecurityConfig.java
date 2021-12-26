package com.example.oauthtest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuthService oAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // db접속 위해
                .headers().frameOptions().disable()
                .and()
                .oauth2Login() // OAuth2 로그인 설정 시작
                .userInfoEndpoint() // OAuth2 로그인 성공 후 사용자 정보를 가져오는 설정
                .userService(oAuthService); //OAuth2 로그인 성공 시, UserService 인터페이스 구현체 등록
    }
}

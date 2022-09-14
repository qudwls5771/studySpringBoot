package com.example.team_pro_ex.com.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception{
        // 스프링부트 교과서 418페이지 참고
        // 1. authorizeRequests() : 사용자 인증과 권한을 설정
        // 1-1. antMatchers() : 매칭되는 URL패턴들에 대한 접근 허용
        // 1-1-1. permitAll() : 모든 사용자에게 접근허용
        // 1-1-2. hasRole("권한") : 특정 권한을 가진 사용자만 접근 허용

        // 2. formLogin() : 로그인 페이지 설정
        // 2.1. loginPage("/login") : 로그인이 필요한 url로 접근하면 '/login' 화면으로 이동

        // 3. logout : 로그아웃 페이지 설정
        // 3-1. logoutUrl("/logout") : 로그아웃을 처리하는 페이지 설정

        // 4. crsf() : crsf는 크로스 사이트 위조 요청에 대한 설정
        // 4-1. disable() : RESTfull을 사용하기 위해서는 csrf기능을 비활성화해야 함

        security.authorizeRequests().antMatchers("/").permitAll();
        security.authorizeRequests().antMatchers("Member/memberLogin/**").authenticated();
        security.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        security.csrf().disable();
        security.formLogin().loginPage("Member/Login").defaultSuccessUrl("memberLogin", true);
    }



}

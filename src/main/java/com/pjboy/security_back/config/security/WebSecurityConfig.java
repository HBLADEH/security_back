package com.pjboy.security_back.config.security;

import com.pjboy.security_back.config.security.custom.CustomizeAuthenticationEntryPoint;
import com.pjboy.security_back.config.security.custom.CustomizeAuthenticationFailureHandler;
import com.pjboy.security_back.config.security.custom.CustomizeAuthenticationSuccessHandler;
import com.pjboy.security_back.config.security.custom.CustomizeLogoutSuccessHandler;
import com.pjboy.security_back.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @program: security_back
 * @description: Spring Security 核心配置
 * @author: BLADE
 * @create: 2020-04-22 19:53
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomizeAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  CustomizeLogoutSuccessHandler logoutSuccessHandler;

  @Autowired
  CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  CustomizeAuthenticationFailureHandler authenticationFailureHandler;



  @Bean
  public UserDetailsService userDetailsService() {
    // 获取用户账号密码以及权限等信息
    return new UserDetailsServiceImpl();
  }


  /**
   * 用户密码加密
   *
   * @return BCryptPasswordEncoder
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    // 设置默认加密方式 (强hash方式加密)
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 配置认证方式等
    auth.userDetailsService(userDetailsService());
    //super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //http.cors().and().csrf().disable();
    // http 相关的配置, 包括登入登出、异常处理、会话管理等


    http.authorizeRequests().
            and().logout().
            permitAll().
            logoutSuccessHandler(logoutSuccessHandler).
            deleteCookies("JSESSIONID").
            and().formLogin().
            permitAll().//允许所有用户
            successHandler(authenticationSuccessHandler).
            failureHandler(authenticationFailureHandler).
            // 设置权限,只允许拥有 query_user 权限的用户访问 /getUsers URL
            //.antMatchers("/getUsers").hasAuthority("query_user")
            and().exceptionHandling().
            authenticationEntryPoint(authenticationEntryPoint);
    super.configure(http);
  }
}

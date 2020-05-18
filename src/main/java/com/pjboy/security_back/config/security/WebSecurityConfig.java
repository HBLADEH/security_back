package com.pjboy.security_back.config.security;

import com.pjboy.security_back.config.security.custom.*;
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

  @Autowired
  CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;



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
    // http 相关的配置, 包括登入登出、异常处理、会话管理等
    http.cors().and().csrf().disable();


    http.authorizeRequests().
            // 设置权限,只允许拥有 query_user 权限的用户访问 /getUsers URL
            //antMatchers("/getUser").hasAuthority("query_user").
            // 异常处理 (权限拒绝或者登录失败等等)
            and().exceptionHandling().
            authenticationEntryPoint(authenticationEntryPoint). // 匿名用户访问无权限资源时的自定义异常处理
            // 登录
            and().formLogin().// 放行登录接口
            permitAll(). //允许所有用户
            successHandler(authenticationSuccessHandler). // 登录成功的处理
            failureHandler(authenticationFailureHandler). // 登录失败的处理
            // 登出
            and().logout().
            permitAll(). //允许所有用户
            logoutSuccessHandler(logoutSuccessHandler). // 登出成功的处理
            deleteCookies("JSESSIONID"). // 删除 cookie
            // 限制账号只能一个用户使用
            and().sessionManagement().
            maximumSessions(1).
            expiredSessionStrategy(sessionInformationExpiredStrategy); // 会话过期处理 (或者被挤下线处理)
    super.configure(http);
  }
}

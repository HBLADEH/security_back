package com.pjboy.security_back.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: security_back
 * @description: Spring Security 核心配置
 * @author: BLADE
 * @create: 2020-04-22 19:53
 **/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 配置认证方式等
    super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // http 相关的配置, 包括登入登出、异常处理、会话管理等
    super.configure(http);
  }
}

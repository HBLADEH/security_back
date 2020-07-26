package com.pjboy.security_back.config.security;

import com.pjboy.security_back.config.security.custom.*;
import com.pjboy.security_back.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @program: security_back
 * @description: Spring Security 核心配置
 * @author: BLADE
 * @create: 2020-04-22 19:53
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // 自定义屏蔽未授权用户登录
  @Autowired
  CustomizeAuthenticationEntryPoint authenticationEntryPoint;

  // 自定义登出成功处理
  @Autowired
  CustomizeLogoutSuccessHandler logoutSuccessHandler;

  // 自定义登录成功处理器
  @Autowired
  CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

  // 自定义登录失败处理
  @Autowired
  CustomizeAuthenticationFailureHandler authenticationFailureHandler;

  // 自定义处理会话过期或者账号被挤下线
  @Autowired
  CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

  // 访问决策管理器
  @Autowired
  CustomizeAccessDecisionManager accessDecisionManager;

  // 实现权限拦截
  @Autowired
  CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

  // 自定义权限拦截器
  @Autowired
  private CustomizeAbstractSecurityInterceptor securityInterceptor;

  
  @Bean
  public UserDetailsService userDetailsService() {
    // 获取用户账号密码以及权限等信息
    return new UserDetailsServiceImpl(); // 返回自定义用户登录
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
    // 配置自定义认证方式等
    auth.userDetailsService(userDetailsService());
    //super.configure(auth);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // http 相关的配置, 包括登入登出、异常处理、会话管理等
    http.cors().and().csrf().disable();
    http.authorizeRequests().
            withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
              @Override
              public <O extends FilterSecurityInterceptor> O postProcess(O o) {
               o.setAccessDecisionManager(accessDecisionManager); // 决策管理器
               o.setSecurityMetadataSource(securityMetadataSource); //安全元处理数据源
               return o;
              }
            }).
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
    http.addFilterBefore(securityInterceptor,FilterSecurityInterceptor.class);
  }
}

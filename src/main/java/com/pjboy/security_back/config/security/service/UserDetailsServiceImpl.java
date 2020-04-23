package com.pjboy.security_back.config.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @program: security_back
 * @description: 用户登录认证逻辑
 * @author: BLADE
 * @create: 2020-04-22 20:13
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 需要构造出 org.springframework.security.core.userdetails.User 对象并返回
    return null;
  }
}

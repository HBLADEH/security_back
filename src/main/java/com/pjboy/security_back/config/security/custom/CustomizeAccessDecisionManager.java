package com.pjboy.security_back.config.security.custom;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @program: security_back
 * @description: 访问决策管理器
 * @author: BLADE
 * @create: 2020-06-17 10:31
 **/
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {
  @Override
  public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
    Iterator<ConfigAttribute> iterator = collection.iterator();
    while (iterator.hasNext()) {
      ConfigAttribute ca = iterator.next();
      // 当前请求所需要的权限
      String needRole = ca.getAttribute();
      // 当前用户所拥有的权限
      Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
      for (GrantedAuthority authority : authorities) {
        if (authority.getAuthority().equals(needRole)) {
          return;
        }
      }
    }
    throw new AccessDeniedException("权限不足！");
  }

  // 设置为 true
  @Override
  public boolean supports(ConfigAttribute configAttribute) {
    return true;
  }

  // 设置为 true

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}

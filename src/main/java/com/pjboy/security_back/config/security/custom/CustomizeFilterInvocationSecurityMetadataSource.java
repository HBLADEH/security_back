package com.pjboy.security_back.config.security.custom;

import com.pjboy.security_back.entity.SysPermission;
import com.pjboy.security_back.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @program: security_back
 * @description: 安全数据源过滤器
 * @author: BLADE
 * @create: 2020-05-19 23:54
 **/
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
  AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Autowired
  SysPermissionService sysPermissionService;


  @Override
  public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
    // 获取请求地址
    String requestURL = ((FilterInvocation) o).getRequestUrl();
    // 查询具体某个接口的权限
    List<SysPermission> permissionList = sysPermissionService.selectListByPath(requestURL);
    if (permissionList == null || permissionList.size() == 0) {
      // 如果该路径没有设置任何权限(在数据库里找不到该路径的任何配置),则说明该路径是每个人都可以访问的
      return null;
    }
    String[] attributes = new String[permissionList.size()];
    for (int i = 0; i < permissionList.size(); i++) {
      attributes[i] = permissionList.get(i).getPermissionCode();
    }
    return SecurityConfig.createList(attributes);
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  // 这里设置为 true
  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}

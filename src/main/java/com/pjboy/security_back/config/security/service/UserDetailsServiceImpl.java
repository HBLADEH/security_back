package com.pjboy.security_back.config.security.service;

import com.pjboy.security_back.entity.SysPermission;
import com.pjboy.security_back.entity.SysUser;
import com.pjboy.security_back.service.SysPermissionService;
import com.pjboy.security_back.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: security_back
 * @description: 用户登录认证逻辑
 * @author: BLADE
 * @create: 2020-04-22 20:13
 **/
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private SysUserService sysUserService;

  @Autowired
  private SysPermissionService sysPermissionService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 需要构造出 org.springframework.security.core.userDetails.User 对象并返回
    if (username == null || "".equals(username)) {
      throw new RuntimeException("用户名不能为空");
    }

    // 首先根据用户名查询用户
    SysUser sysUser = sysUserService.selectByUserName(username);
    if (sysUser == null) {
      throw new RuntimeException("该用户不存在");
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    // 用户存在则获取该用户拥有的权限
    List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
    // 声明该用户权限
    sysPermissions.forEach(sysPermission -> {
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
      grantedAuthorities.add(grantedAuthority);
    });

    // 返回一个 User 对象
    return new User(sysUser.getAccount(), sysUser.getPassword(),sysUser.getEnabled(),sysUser.getNotExpired(),sysUser.getCredentialsNotExpired(),sysUser.getAccountNotLocked(),grantedAuthorities);
  }
}

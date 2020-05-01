package com.pjboy.security_back.config.security.custom;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pjboy.security_back.entity.SysUser;
import com.pjboy.security_back.service.SysUserService;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @program: security_back
 * @description: 自定义登录成功处理器
 * @author: BLADE
 * @create: 2020-05-01 10:37
 **/
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  SysUserService sysUserService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // mybatis-plus 实体操作类 UpdateWrapper 条件是 username
    UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("username", userDetails.getUsername());

    // 更新用户的登陆时间、更新人、更新时间等字段
    SysUser sysUser = new SysUser();
    sysUser.setLastLoginTime(new Date());
    sysUser.setUpdateUser(sysUser.getId());
    sysUser.setUpdateTime(new Date());
    sysUserService.update(sysUser, updateWrapper);

    // 这里还可以再给予前台一些东西, 返回给前台该用户有哪些菜单权限
    // 进而动态现实前台菜单

    // 返回 json 体
    JsonResult result = ResultTool.success();

    // 处理编码方式
    response.setContentType("text/json;charset=utf-8");

    // 把 result 塞入 HttpServletResponse 中 返回给前台
    response.getWriter().write(JSON.toJSONString(result));
  }
}

package com.pjboy.security_back.controller;

import com.pjboy.security_back.entity.SysUser;
import com.pjboy.security_back.service.SysUserService;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Security;
import java.util.Enumeration;
import java.util.List;

/**
 * @program: security_back
 * @description: 用户控制器
 * @author: BLADE
 * @create: 2020-04-29 10:34
 **/
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  SysUserService sysUserService;

  @GetMapping("/getUser")
  public JsonResult getUser(HttpServletRequest request) {
    List<SysUser> users = sysUserService.list();
    return ResultTool.success(users);
  }
  @PostMapping("/info")
  public JsonResult getUserInfo(HttpServletRequest request) {
    // 首先获取 session 对象
    HttpSession session = request.getSession();

    // 去除 session 域中的所有属性名
    Enumeration attributeNames = session.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      System.out.println(attributeNames.nextElement());
    }

    // 获取 spring_security_context
    Object spring_security_context = session.getAttribute("SPRING_SECURITY_CONTEXT");
    System.out.println(spring_security_context);
    SecurityContext securityContext = (SecurityContext) spring_security_context;

    // 获取认证信息
    Authentication authentication = securityContext.getAuthentication();
    // 获取用户详情
    Object principal = authentication.getPrincipal();
    return ResultTool.success();
  }

}

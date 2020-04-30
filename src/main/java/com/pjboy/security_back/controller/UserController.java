package com.pjboy.security_back.controller;

import com.pjboy.security_back.entity.SysUser;
import com.pjboy.security_back.service.SysUserService;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: security_back
 * @description: 用户控制器
 * @author: BLADE
 * @create: 2020-04-29 10:34
 **/
@RestController
public class UserController {
  @Autowired
  SysUserService sysUserService;

  @GetMapping("/getUser")
  public JsonResult getUser() {
    List<SysUser> users = sysUserService.list();
    return ResultTool.success(users);
  }

}

package com.pjboy.security_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pjboy.security_back.entity.SysUser;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-04-23 18:26:04
 */
public interface SysUserService extends IService<SysUser> {
  
  /**
  * @Description: 根据账号查询用户
  * @Param: [account]
  * @return: com.pjboy.security_back.entity.SysUser
  * @Author: BLADE
  * @Date: 2020/4/23 0023
  */
  SysUser selectByUserName(String username);
}
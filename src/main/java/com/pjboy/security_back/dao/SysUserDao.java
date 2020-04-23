package com.pjboy.security_back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjboy.security_back.entity.SysUser;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-23 18:09:00
 */
public interface SysUserDao extends BaseMapper<SysUser> {
  /**
   * @Description: 根据账号查询用户
   * @Param: [account]
   * @return: com.pjboy.security_back.entity.SysUser
   * @Author: BLADE
   * @Date: 2020/4/23 0023
   */
  SysUser selectByAccount(String account);
}
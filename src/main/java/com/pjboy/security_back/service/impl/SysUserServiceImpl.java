package com.pjboy.security_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjboy.security_back.dao.SysUserDao;
import com.pjboy.security_back.entity.SysUser;
import com.pjboy.security_back.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-04-23 18:26:04
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

  @Resource
  private SysUserDao sysUserDao;

  @Override
  public SysUser selectByAccount(String account) {
    return this.sysUserDao.selectByAccount(account);
  }
}
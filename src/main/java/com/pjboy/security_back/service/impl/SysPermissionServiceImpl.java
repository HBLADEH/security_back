package com.pjboy.security_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjboy.security_back.dao.SysPermissionDao;
import com.pjboy.security_back.entity.SysPermission;
import com.pjboy.security_back.service.SysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-04-25 11:19:00
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

  @Resource
  private SysPermissionDao sysPermissionDao;

  @Override
  public List<SysPermission> selectListByUser(Integer userId) {
    return this.sysPermissionDao.selectListByUser(userId);
  }
}
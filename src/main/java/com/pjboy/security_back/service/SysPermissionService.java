package com.pjboy.security_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pjboy.security_back.entity.SysPermission;

import java.util.List;

/**
 * 权限表(SysPermission)表服务接口
 *
 * @author makejava
 * @since 2020-04-25 11:19:00
 */
public interface SysPermissionService extends IService<SysPermission> {
  List<SysPermission> selectListByUser(Integer userId);
}
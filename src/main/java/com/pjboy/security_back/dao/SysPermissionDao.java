package com.pjboy.security_back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjboy.security_back.entity.SysPermission;

import java.util.List;

/**
 * 权限表(SysPermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-25 11:19:00
 */
public interface SysPermissionDao extends BaseMapper<SysPermission> {
  List<SysPermission> selectListByUser(Integer userId);
}
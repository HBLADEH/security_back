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
  
  /**
  * @Description: 查询用户的权限列表
  * @Param: [userId]
  * @return: java.util.List<com.pjboy.security_back.entity.SysPermission>
  * @Author: BLADE
  * @Date: 2020/6/17
  */
  List<SysPermission> selectListByUser(Integer userId);
  
  /**
  * @Description: 查询具体的某个接口对应权限
  * @Param: [path]
  * @return: java.util.List<com.pjboy.security_back.entity.SysPermission>
  * @Author: BLADE
  * @Date: 2020/6/17
  */
  List<SysPermission>selectListByPath(String path);
}
package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .dao.SysPermissionDao;
import .entity.SysPermission;
import .service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-04-23 18:51:33
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

}
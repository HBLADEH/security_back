package com.pjboy.security_back.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 权限表(SysPermission)表实体类
 *
 * @author makejava
 * @since 2020-04-25 11:19:00
 */
@Data
@SuppressWarnings("serial")
public class SysPermission extends Model<SysPermission> {
  @TableId(type = IdType.AUTO)
    /**主键id*/
    private Integer id;
    /**权限code*/
    private String permissionCode;
    /**权限名*/
    private String permissionName;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }
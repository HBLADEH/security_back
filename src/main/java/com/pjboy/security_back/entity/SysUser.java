package com.pjboy.security_back.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.EqualsAndHashCode;

/**
 * 用户表(SysUser)表实体类
 *
 * @author makejava
 * @since 2020-04-23 18:08:59
 */
@Data
@SuppressWarnings("serial")
public class SysUser extends Model<SysUser> {
  @TableId(type = IdType.AUTO)
    
    private Integer id;
    /**账号*/
    private String account;
    /**用户名*/
    private String userName;
    /**用户密码*/
    private String password;
    /**上一次登录时间*/
    private Date lastLoginTime;
    /**账号是否可用。默认为1（可用）*/
    private Boolean enabled;
    /**是否过期。默认为1（没有过期）*/
    private Boolean notExpired;
    /**账号是否锁定。默认为1（没有锁定）*/
    private Boolean accountNotLocked;
    /**证书（密码）是否过期。默认为1（没有过期）*/
    private Boolean credentialsNotExpired;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
    /**创建人*/
    private Integer createUser;
    /**修改人*/
    private Integer updateUser;

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
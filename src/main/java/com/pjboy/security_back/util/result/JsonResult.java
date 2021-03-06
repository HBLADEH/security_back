package com.pjboy.security_back.util.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: security_back
 * @description: 统一返回实体
 * @author: BLADE
 * @create: 2020-04-22 18:19
 **/
@Data
public class JsonResult<T> implements Serializable {
  private boolean success;
  private Integer errorCode;
  private String errorMsg;
  private T data;

  public JsonResult() {
  }

  /**
   * @Description: 只有 success 的构造函数
   * @Param: [success]
   * @Author: BLADE
   * @Date: 2020/4/22 0022
   */
  public JsonResult(boolean success) {
    this.success = success;
    this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
    this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
  }

  /**
  * @Description: 传入参数有 success 和状态码
  * @Param: [success, resultEnum]
  * @Author: BLADE
  * @Date: 2020/4/22 0022
  */
  public JsonResult(boolean success, ResultCode resultEnum) {
    this.success = success;
    this.errorCode = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
    this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
  }

  /**
  * @Description: 传入参数有 success 和数据
  * @Param: [success, data]
  * @Author: BLADE
  * @Date: 2020/4/22 0022
  */
  public JsonResult(boolean success, T data) {
    this.success = success;
    this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
    this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    this.data = data;
  }

  /**
  * @Description: 传入参数有 success 状态码 数据
  * @Param: [success, resultEnum, data]
  * @Author: BLADE
  * @Date: 2020/4/22 0022
  */
  public JsonResult(boolean success, ResultCode resultEnum, T data) {
    this.success = success;
    this.errorCode = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
    this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    this.data = data;
  }
}

package com.pjboy.security_back.util;


/**
* @Description: 定义返回码
* @Author: BLADE
* @Date: 2020/4/22 0022
*/
public enum ResultCode {
  /*  */
  SUCCESS(200, "成功");

  private Integer code;
  private String message;

  ResultCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}

package com.pjboy.security_back.util.result;

/**
 * @program: security_back
 * @description: 返回结构体的工具类
 * @author: BLADE
 * @create: 2020-04-22 18:33
 **/
public class ResultTool {
  public static JsonResult success() {
    return new JsonResult(true);
  }

  public static <T> JsonResult<T> sucess(T data) {
    return new JsonResult<T>(true, data);
  }

  public static JsonResult fail() {
    return new JsonResult(false);
  }

  public static JsonResult fail(ResultCode resultEnum) {
    return new JsonResult(false, resultEnum);
  }
}

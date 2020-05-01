package com.pjboy.security_back.config.security.custom;

import com.alibaba.fastjson.JSON;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultCode;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security_back
 * @description: 登录失败处理器
 * @author: BLADE
 * @create: 2020-05-01 11:13
 **/
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    // 返回 json 体
    JsonResult result = null;

    if (exception instanceof AccountExpiredException) {
      // 账号过期
      result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
    } else if (exception instanceof BadCredentialsException) {
      //  密码错误
      result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
    } else if (exception instanceof CredentialsExpiredException) {
      // 密码过期
      result = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
    } else if (exception instanceof DisabledException) {
      // 账号不可用
      result = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
    } else if (exception instanceof LockedException) {
      // 账号锁定
      result = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
    } else if (exception instanceof InternalAuthenticationServiceException) {
      // 账号不存在
      result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
    } else {
      // 其他错误
      result = ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    // 设置返回编码
    response.setContentType("text/json;charset=utf-8");
    // 返回数据
    response.getWriter().write(JSON.toJSONString(result));
  }
}

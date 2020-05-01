package com.pjboy.security_back.config.security.custom;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultCode;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security_back
 * @description: 自定义屏蔽未授权用户登录, 并返回自定义 json 体
 * @author: BLADE
 * @create: 2020-04-29 15:49
 **/
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {


  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
    httpServletResponse.setContentType("text/json;charset=utf-8");
    httpServletResponse.getWriter().write(JSON.toJSONString(result));
  }
}

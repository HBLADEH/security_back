package com.pjboy.security_back.config.security.custom;

import com.alibaba.fastjson.JSON;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security_back
 * @description: 自定义登出成功处理
 * @author: BLADE
 * @create: 2020-05-01 17:24
 **/
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    JsonResult result = ResultTool.success();
    response.setContentType("text/json;charset=utf-8");
    response.getWriter().write(JSON.toJSONString(result));
  }
}

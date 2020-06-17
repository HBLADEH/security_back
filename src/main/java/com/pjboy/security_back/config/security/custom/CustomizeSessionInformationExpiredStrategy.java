package com.pjboy.security_back.config.security.custom;

import com.alibaba.fastjson.JSON;
import com.pjboy.security_back.util.result.JsonResult;
import com.pjboy.security_back.util.result.ResultCode;
import com.pjboy.security_back.util.result.ResultTool;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security_back
 * @description: 自定义处理会话过期或者账号被挤下线
 * @author: BLADE
 * @create: 2020-05-18 23:54
 **/
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
  @Override
  public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
    JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
    HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
    response.setContentType("text/json;charset=utf-8");
    response.getWriter().write(JSON.toJSONString(result));
  }
}

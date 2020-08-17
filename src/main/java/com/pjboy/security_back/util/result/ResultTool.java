package com.pjboy.security_back.util.result;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

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

  public static <T> JsonResult<T> success(T data) {
    return new JsonResult<T>(true, data);
  }

  public static JsonResult fail() {
    return new JsonResult(false);
  }

  public static JsonResult fail(ResultCode resultEnum) {
    return new JsonResult(false, resultEnum);
  }

  public static Authentication getAuthenticationByRequest(HttpServletRequest request) {
    // 首先获取 session 对象
    HttpSession session = request.getSession();

    // 去除 session 域中的所有属性名
    Enumeration attributeNames = session.getAttributeNames();

    //while (attributeNames.hasMoreElements()) {
    //  System.out.println(attributeNames.nextElement());
    //}

    // 获取 spring_security_context
    Object spring_security_context = session.getAttribute("SPRING_SECURITY_CONTEXT");

    //System.out.println(spring_security_context);

    SecurityContext securityContext = (SecurityContext) spring_security_context;

    // 获取认证信息
    Authentication authentication = securityContext.getAuthentication();
    return authentication;
  }
}

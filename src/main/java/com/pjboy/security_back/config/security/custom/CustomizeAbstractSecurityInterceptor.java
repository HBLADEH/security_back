package com.pjboy.security_back.config.security.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: security_back
 * @description: 自定义权限拦截器
 * @author: BLADE
 * @create: 2020-05-19 19:18
 **/

@Service
public class CustomizeAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

  // 安全元数据源
  @Autowired
  private FilterInvocationSecurityMetadataSource securityMetadataSource;


  /**
  * @Description: 设置访问决策管理器 (用自定义的决策管理器替换)
  * @Param: [accessDecisionManager]
  * @return: void
  * @Author: BLADE
  * @Date: 2020/7/26
  */
  @Autowired
  public void setMyAccessDecisionManager(CustomizeAccessDecisionManager accessDecisionManager) {
    super.setAccessDecisionManager(accessDecisionManager);
  }

  @Override
  public Class<?> getSecureObjectClass() {
    return FilterInvocation.class;
  }

  @Override
  public SecurityMetadataSource obtainSecurityMetadataSource() {
    return this.securityMetadataSource;
  }
  
  /**
  * @Description: 执行过滤
  * @Param: [servletRequest, servletResponse, filterChain]
  * @return: void
  * @Author: BLADE
  * @Date: 2020/7/26
  */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
    invoke(fi);
  }

  public void invoke(FilterInvocation fi) throws IOException, ServletException {
    //fi里面有一个被拦截的url
    //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
    //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
    InterceptorStatusToken token = super.beforeInvocation(fi);
    try {
      //执行下一个拦截器
      fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
    } finally {
      super.afterInvocation(token, null);
    }
  }
}

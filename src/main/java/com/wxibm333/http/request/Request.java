package com.wxibm333.http.request;

import com.intellij.psi.PsiElement;
import java.util.Set;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-18 13:41
 */
public class Request {

  private Method method;
  private Set<String> pathAbsolute;

  public void setMethod(Method method) {
    this.method = method;
  }

  public Method getMethod() {
    return method;
  }

  public Set<String> getPathAbsolute() {
    return pathAbsolute;
  }

  public void setPathAbsolute(Set<String> pathAbsolute) {
    this.pathAbsolute = pathAbsolute;
  }

  /**
   * 请求体方法
   *
   * @author wangXin
   * @version v1.0.0
   * @date 2020-05-18 13:43
   */
  public enum Method {
    /**
     * GET 方法
     */
    GET,
    /**
     * PUT 方法
     */
    PUT,
    /**
     * POST 方法
     */
    POST,
    /**
     * PATCH 方法
     */
    PATCH,
    /**
     * DELETE 方法
     */
    DELETE,
    /**
     * 支持所有方法
     */
    ALL
  }
}

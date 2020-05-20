package com.wxibm333.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.spring.web.mvc.mapping.UrlMapping;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-20 14:41
 */
public abstract class RestService {

  public static RestService getInstance() {
    return ServiceManager.getService(RestService.class);
  }

  public abstract Set<UrlMapping<?>> getUrlFromProject(@NotNull Project project);

  public abstract @NotNull Set<UrlMapping<?>> matchUrl(@NotNull Set<UrlMapping<?>> urlMappings,
      @NotNull String url, @Nullable String httpMethod);
}

package com.wxibm333.service.impl;

import com.google.common.collect.Sets;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.spring.web.mvc.jam.RequestMethod;
import com.intellij.spring.web.mvc.mapping.UrlMapping;
import com.intellij.spring.web.mvc.model.mappings.UrlMappingPsiBasedElement;
import com.intellij.spring.web.mvc.services.SpringMvcService;
import com.wxibm333.service.RestService;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-20 14:57
 */
public class RestServiceImpl extends RestService {

  @Override
  public Set<UrlMapping<?>> getUrlFromProject(@NotNull Project project) {
    ModuleManager moduleManager = ModuleManager.getInstance(project);
    Module[] modules = moduleManager.getModules();
    Set<UrlMapping<?>> urlMappings = Sets.newHashSet();
    SpringMvcService springMvcService = SpringMvcService.getInstance();
    for (Module module : modules) {
      urlMappings.addAll(springMvcService.getUrlMappings(module));
    }
    return urlMappings;
  }

  @Override
  public @NotNull Set<UrlMapping<?>> matchUrl(@NotNull Set<UrlMapping<?>> urlMappings,
      @NotNull String url, @Nullable String httpMethod) {
    Set<UrlMapping<?>> returnUrlMappings = Sets.newHashSet();
    for (UrlMapping<?> urlMapping : urlMappings) {
      UrlMappingPsiBasedElement psiBasedElement = (UrlMappingPsiBasedElement) urlMapping;
      boolean canResolve = psiBasedElement.canResolve(url);
      if (canResolve) {
        if (Objects.isNull(httpMethod)) {
          returnUrlMappings.add(psiBasedElement);
        } else {
          RequestMethod[] method = psiBasedElement.getMethod();
          boolean matchHttpMethod = false;
          for (RequestMethod requestMethod : method) {
            if (!matchHttpMethod) {
              matchHttpMethod = requestMethod.name().equalsIgnoreCase(httpMethod);
            }
          }
          if (matchHttpMethod) {
            returnUrlMappings.add(psiBasedElement);
          }
        }
      }
    }
    return returnUrlMappings;
  }
}

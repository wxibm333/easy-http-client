package com.wxibm333.util;

import com.google.common.collect.Sets;
import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.util.PsiTreeUtil;
import com.wxibm333.constant.SpringClassNames;
import com.wxibm333.http.request.Request;
import com.wxibm333.http.request.Request.Method;
import java.util.Collection;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-14 16:11
 */
public class JavaUtil {

  public static boolean isRestMethod(PsiElement element) {
    if (element instanceof PsiMethod) {
      PsiAnnotation psiAnnotation = AnnotationUtil
          .findAnnotation((PsiMethod)element, SpringClassNames.REQUEST_COLLECTION);
      return psiAnnotation != null;
    }
    return false;
  }

  public static @Nullable Request getRestPathAbsolute(PsiMethod method) {
    PsiElement parent = method.getParent();
    if (parent instanceof PsiClass && JavaUtil.isRestMethod(method)) {
      Set<String> classPathAbsolute = JavaUtil.getClassPathAbsolute((PsiClass) parent);
      Request request = JavaUtil.getMethodPathAbsolute(method);
      if (classPathAbsolute != null && request != null && request.getPathAbsolute() != null) {
        Set<String> path = Sets.newHashSet();
        for (String classPath : classPathAbsolute) {
          classPath = ToolUtil.formatPath(classPath);
          for (String methodPath : request.getPathAbsolute()) {
            String handlerMethodPath = ToolUtil.removeFrontAndRearDiagonal(methodPath);
            path.add(classPath.concat(handlerMethodPath));
          }
        }
        request.setPathAbsolute(path);
        return request;
      }
    }
    return null;
  }

  public static Request.Method getMethod(PsiMethod method) {
    PsiAnnotation psiAnnotation = AnnotationUtil
        .findAnnotation(method, SpringClassNames.REQUEST_COLLECTION);
    if (psiAnnotation != null) {
      String qualifiedName = psiAnnotation.getQualifiedName();
      if (qualifiedName != null) {
        switch (qualifiedName) {
          case SpringClassNames.REQUEST_GET:
            return Request.Method.GET;
          case SpringClassNames.REQUEST_PUT:
            return Request.Method.PUT;
          case SpringClassNames.REQUEST_POST:
            return Method.POST;
          case SpringClassNames.REQUEST_PATCH:
            return Method.PATCH;
          case SpringClassNames.REQUEST_DELETE:
            return Method.DELETE;
          default:
            return Request.Method.ALL;
        }
      }
    }
    return null;
  }

  public static @Nullable Request getMethodPathAbsolute(PsiMethod method) {
    if (JavaUtil.isRestMethod(method)) {
      PsiAnnotation psiAnnotation = AnnotationUtil
          .findAnnotation(method, SpringClassNames.REQUEST_COLLECTION);

      if (psiAnnotation != null) {
        Request request = new Request();
        request.setMethod(getMethod(method));
        Set<String> pathSet = RequestAnnotationUtil
            .getPathByRequestMappingAnnotation(psiAnnotation);
        request.setPathAbsolute(pathSet);
        return request;
      }
    }
    return null;
  }


  public static @Nullable Set<String> getClassPathAbsolute(PsiClass psiClass) {
    // 只获取类上请求路径
    PsiModifierList modifierList = PsiTreeUtil.getChildOfType(psiClass, PsiModifierList.class);
    Collection<PsiAnnotation> annotations = PsiTreeUtil
        .findChildrenOfType(modifierList, PsiAnnotation.class);
    boolean isRestController = false;
    Set<String> classPathSet = Sets.newHashSet();
    for (PsiAnnotation annotation : annotations) {
      isRestController = isRestController || SpringClassNames.CONTROLLER_COLLECTION
          .contains(annotation.getQualifiedName());
      Set<String> pathSet = RequestAnnotationUtil
          .getPathByRequestMappingAnnotation(annotation);
      if (pathSet != null) {
        classPathSet.addAll(pathSet);
      }
    }
    if (isRestController) {
      return classPathSet;
    }
    return null;
  }
}

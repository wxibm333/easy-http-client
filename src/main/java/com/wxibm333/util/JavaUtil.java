package com.wxibm333.util;

import com.google.common.collect.Sets;
import com.intellij.lang.jvm.annotation.JvmAnnotationArrayValue;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttribute;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttributeValue;
import com.intellij.lang.jvm.annotation.JvmAnnotationConstantValue;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.util.PsiTreeUtil;
import com.wxibm333.constant.Common;
import com.wxibm333.constant.SpringClassNames;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-14 16:11
 */
public class JavaUtil {

  public static boolean isRestMethod(PsiElement element) {
    if (element instanceof PsiMethod) {
      Collection<PsiAnnotation> annotations = PsiTreeUtil
          .findChildrenOfType(element, PsiAnnotation.class);
      for (PsiAnnotation annotation : annotations) {
        if (SpringClassNames.REQUEST_COLLECTION.contains(annotation.getQualifiedName())) {
          return true;
        }
      }
    }
    return false;
  }

  public static @Nullable Set<String> getRestPathAbsolute(PsiMethod method) {
    PsiElement parent = method.getParent();
    if (parent instanceof PsiClass && JavaUtil.isRestMethod(method)) {
      Set<String> classPathAbsolute = JavaUtil.getClassPathAbsolute((PsiClass) parent);
      Set<String> methodPathAbsolute = JavaUtil.getMethodPathAbsolute(method);
      if (classPathAbsolute != null && methodPathAbsolute != null) {
        Set<String> path = Sets.newHashSet();
        for (String classPath : classPathAbsolute) {
          classPath = ToolUtil.formatPath(classPath);
          for (String methodPath : methodPathAbsolute) {
            String handlerMethodPath = ToolUtil.removeFrontAndRearDiagonal(methodPath);
            path.add(classPath.concat(handlerMethodPath));
          }
        }
        return path;
      }
    }
    return null;
  }

  public static @Nullable Set<String> getMethodPathAbsolute(PsiMethod method) {
    // todo 需要处理获取请求路径httpMethod,参数,body等问题
    Collection<PsiAnnotation> annotations = PsiTreeUtil
        .findChildrenOfType(method, PsiAnnotation.class);
    Set<String> methodPathSet = Sets.newHashSet();
    for (PsiAnnotation annotation : annotations) {
      Set<String> pathSet = AnnotationUtil
          .getPathByRequestMappingAnnotation(annotation);
      if (pathSet != null) {
        methodPathSet.addAll(pathSet);
      }
    }
    if (methodPathSet.size() > 0) {
      return methodPathSet;
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
      Set<String> pathSet = AnnotationUtil
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

package com.wxibm333.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.ws.http.request.HttpRequestPsiFile;
import com.intellij.ws.http.request.completion.HttpRequestHostIndex;
import com.intellij.ws.http.request.psi.HttpPathAbsolute;
import com.intellij.ws.http.request.psi.HttpRequest;
import com.intellij.ws.http.request.psi.HttpRequestCompositeElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-14 16:33
 */
public class HttpRequestUtil {

  public static Collection<VirtualFile> getHttpRequestFileCandidates(Project project,
      final GlobalSearchScope scope) {
    Collection<VirtualFile> virtualFiles = new HashSet<>();
    FileBasedIndex instance = FileBasedIndex.getInstance();
    Collection<String> allKeys = instance.getAllKeys(HttpRequestHostIndex.KEY, project);
    allKeys.forEach(key -> {
      Collection<VirtualFile> containingFiles = instance
          .getContainingFiles(HttpRequestHostIndex.KEY, key, scope);
      virtualFiles.addAll(containingFiles);
    });
    return virtualFiles;
  }

  public static Collection<HttpRequestPsiFile> getHttpRequestFile(Project project) {
    return HttpRequestUtil.getHttpRequestFile(project, null);
  }

  public static Collection<HttpRequestPsiFile> getHttpRequestFile(Project project,
      @Nullable GlobalSearchScope globalSearchScope) {
    final Collection<VirtualFile> list = HttpRequestUtil.getHttpRequestFileCandidates(project,
        globalSearchScope != null ? globalSearchScope : GlobalSearchScope.allScope(project));
    final Collection<HttpRequestPsiFile> result = new ArrayList<>(list.size());
    for (VirtualFile file : list) {
      final PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
      if (psiFile instanceof HttpRequestPsiFile) {
        result.add((HttpRequestPsiFile) psiFile);
      }
    }
    return result;
  }

  public static Collection<HttpRequest> getHttpRequestByHttpPathAbsolute(Project project,
      @NotNull Collection<String> pathSet) {
    Collection<HttpRequestPsiFile> httpRequestPsiFiles = HttpRequestUtil
        .getHttpRequestFile(project, null);
    final Collection<HttpRequest> result = new HashSet<>();
    for (HttpRequestPsiFile httpRequestPsiFile : httpRequestPsiFiles) {
      PsiElement[] requestPsiFileChildren = httpRequestPsiFile.getChildren();
      for (PsiElement psiElement : requestPsiFileChildren) {
        if (psiElement instanceof HttpRequestCompositeElement) {
          HttpRequest httpRequest = PsiTreeUtil
              .getChildOfType(psiElement, HttpRequest.class);
          HttpPathAbsolute httpPathAbsolute = PsiTreeUtil
              .findChildOfType(httpRequest, HttpPathAbsolute.class);
          if (httpPathAbsolute != null) {
            String text = ToolUtil.removeFrontAndRearDiagonal(httpPathAbsolute.getText());
            if (pathSet.contains(text)) {
              result.add(httpRequest);
            }
          }
        }
      }
    }
    return result;
  }
}

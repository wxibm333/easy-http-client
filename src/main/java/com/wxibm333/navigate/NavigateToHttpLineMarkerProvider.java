package com.wxibm333.navigate;

import com.google.common.collect.Sets;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ws.http.request.HttpRequestPsiFile;
import com.intellij.ws.http.request.psi.HttpMethod;
import com.intellij.ws.http.request.psi.HttpPathAbsolute;
import com.intellij.ws.http.request.psi.HttpRequest;
import com.wxibm333.http.request.Request;
import com.wxibm333.util.HttpRequestUtil;
import com.wxibm333.util.JavaUtil;
import icons.RestClientIcons;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

/**
 * java类导航到http文件
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-14 11:49
 */
public class NavigateToHttpLineMarkerProvider extends RelatedItemLineMarkerProvider {

  private List<HttpMethod> mathByHttpPathAbsolute(PsiElement element) {
    Request request = JavaUtil.getRestPathAbsolute((PsiMethod) element);
    List<HttpRequest> httpRequests = HttpRequestUtil
        .getHttpRequestByHttpPathAbsolute(element.getProject(), request);
    return httpRequests.stream().map(HttpRequest::getMethod).collect(Collectors.toList());
  }

  @Override
  protected void collectNavigationMarkers(@NotNull PsiElement element,
      @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
    if (JavaUtil.isRestMethod(element)) {
      Collection<HttpMethod> matchResults = this.mathByHttpPathAbsolute(element);
      if (matchResults != null && !matchResults.isEmpty()) {
        NavigationGutterIconBuilder<PsiElement> builder =
            NavigationGutterIconBuilder.create(RestClientIcons.Request)
                .setAlignment(GutterIconRenderer.Alignment.CENTER)
                .setTargets(matchResults)
                .setTooltipTitle("Navigation to target in http file");
        PsiElement nameIdentifier = ((PsiNameIdentifierOwner) element).getNameIdentifier();
        if (nameIdentifier != null) {
          result.add(builder.createLineMarkerInfo(nameIdentifier));
        }
      }
    }
  }
}

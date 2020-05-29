package com.wxibm333.navigate;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.markup.GutterIconRenderer.Alignment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.spring.web.mvc.mapping.UrlMapping;
import com.intellij.spring.web.mvc.model.mappings.UrlMappingPsiBasedElement;
import com.intellij.spring.web.mvc.services.SpringMvcUtils;
import com.intellij.ws.http.request.psi.HttpPathAbsolute;
import com.intellij.ws.http.request.psi.HttpRequest;
import com.wxibm333.service.RestService;
import com.wxibm333.util.ToolUtil;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * java类导航到http文件
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-14 11:49
 */
public class NavigateToJavaLineMarkerProvider extends RelatedItemLineMarkerProvider {

  private List<PsiElement> matchNavigationElement(HttpRequest httpRequest) {
    HttpPathAbsolute httpPathAbsolute = PsiTreeUtil
        .findChildOfType(httpRequest, HttpPathAbsolute.class);
    List<PsiElement> psiElementList = Lists.newArrayList();
    if (httpPathAbsolute != null) {
      String httpMethod = httpRequest.getHttpMethod();
      RestService restService = RestService.getInstance();
      Set<UrlMapping<?>> urlMappings = restService.getUrlFromProject(httpRequest.getProject());
      String url = ToolUtil.removeFrontAndRearDiagonal(httpPathAbsolute.getText());
      Set<UrlMapping<?>> matchUrlMappings = restService.matchUrl(urlMappings, url, httpMethod);
      for (UrlMapping<?> matchUrlMapping : matchUrlMappings) {
        if (matchUrlMapping instanceof UrlMappingPsiBasedElement) {
          UrlMappingPsiBasedElement psiBasedElement = (UrlMappingPsiBasedElement) matchUrlMapping;
          PsiElement psiDefinition = SpringMvcUtils.getPsiDefinition(psiBasedElement);
          if (psiDefinition != null) {
            PsiElement navigationElement = psiDefinition.getNavigationElement();
            psiElementList.add(navigationElement);
          }
        }
      }
    }
    return psiElementList;
  }

  @Override
  protected void collectNavigationMarkers(@NotNull PsiElement element,
      @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
    if (element instanceof HttpRequest) {
      HttpRequest httpRequest = (HttpRequest) element;
      List<PsiElement> psiElementList = this.matchNavigationElement(httpRequest);
      if (psiElementList.size() > 0) {
        NavigationGutterIconBuilder<PsiElement> builder =
            NavigationGutterIconBuilder.create(AllIcons.Nodes.MethodReference)
                .setAlignment(Alignment.LEFT)
                .setTargets(psiElementList)
                .setTooltipTitle("Navigation to target in java file");
        LeafPsiElement leafPsiElement = PsiTreeUtil.getChildOfType(element, LeafPsiElement.class);
        if(leafPsiElement != null){
          result.add(builder.createLineMarkerInfo(leafPsiElement));
        }
      }
    }
  }
}

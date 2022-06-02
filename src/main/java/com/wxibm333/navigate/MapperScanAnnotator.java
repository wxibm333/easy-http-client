package com.wxibm333.navigate;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.psi.PsiElement;
import com.intellij.spring.SpringApiIcons;
import com.intellij.spring.gutter.SpringAnnotatorBase;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;

/**
 * 参考 SpringAutowiredAnnotator
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2022-05-31 9:57 PM
 */
public final class MapperScanAnnotator extends SpringAnnotatorBase {
    
    @Override
    public String getId() {
        return "MapperScanAnnotator";
    }
    
    @Override
    public String getName() {
        return "MapperScan";
    }
    
    @Override
    public @NotNull Icon getIcon() {
        return SpringApiIcons.ShowAutowiredDependencies;
    }
    
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement psiElement,
            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        
    }
}

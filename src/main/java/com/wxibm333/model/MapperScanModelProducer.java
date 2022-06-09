package com.wxibm333.model;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.spring.SpringLocalModelProducer;
import com.intellij.spring.contexts.model.LocalAnnotationModel;
import com.wxibm333.constant.AnnotationNameConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-04 9:46 AM
 */
public class MapperScanModelProducer implements SpringLocalModelProducer {
    
    @Override
    public @Nullable LocalAnnotationModel create(@NotNull PsiClass psiClass, @NotNull Module module,
            @NotNull Set<String> activeProfiles) {
        PsiAnnotation psiAnnotation = AnnotationUtil.findAnnotation(psiClass, AnnotationNameConstant.MAPPER_SCAN);
        if (Objects.nonNull(psiAnnotation)) {
            return new MapperScanLocalAnnotationModel(psiClass, module, activeProfiles);
        }
        return null;
    }
}

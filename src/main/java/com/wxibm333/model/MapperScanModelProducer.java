package com.wxibm333.model;

import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.spring.SpringLocalModelProducer;
import com.intellij.spring.contexts.model.LocalAnnotationModel;
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
        PsiClass mapperPsiClass = JavaPsiFacade.getInstance(module.getProject())
                .findClass("cn.cloudlizard.file.mapper.FileInfoMapper",
                        GlobalSearchScope.allScope(module.getProject()));
        return Objects.nonNull(mapperPsiClass) ? new MapperScanModel(psiClass, module, activeProfiles) : null;
    }
}

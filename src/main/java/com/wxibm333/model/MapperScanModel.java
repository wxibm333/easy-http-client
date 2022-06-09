package com.wxibm333.model;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.jam.JamStringAttributeElement;
import com.intellij.jam.reflect.JamStringAttributeMeta;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementRef;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.ref.AnnotationChildLink;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.spring.CommonSpringModel;
import com.intellij.spring.contexts.model.CacheableCommonSpringModel;
import com.intellij.spring.contexts.model.LocalAnnotationModel;
import com.intellij.spring.contexts.model.LocalModel;
import com.intellij.spring.model.BeanService;
import com.intellij.spring.model.SpringBeanPointer;
import com.intellij.spring.model.aliasFor.SpringAliasFor;
import com.intellij.spring.model.aliasFor.SpringAliasForUtils;
import com.intellij.spring.model.jam.converters.PackageJamConverter;
import com.intellij.spring.model.jam.stereotype.CustomSpringComponent;
import com.intellij.util.containers.ContainerUtil;
import com.wxibm333.constant.AnnotationNameConstant;
import com.wxibm333.util.AnnoUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>BeansSpringModel</p>
 * <p>ComponentScanPackagesModel</p>
 * <p>AnnotationSpringModelImpl</p>
 * <p>SpringManagerImpl spring 管理服务，控制bean的管理控制模型,-> SpringCombinedModelFactory.createModel 创建model </p>
 * CustomJamComponentScanBase
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-03 9:18 PM
 */
public final class MapperScanModel extends LocalAnnotationModel {
    
    
    public MapperScanModel(@NotNull PsiClass aClass, @NotNull Module module, @NotNull Set<String> activeProfiles) {
        super(aClass, module, activeProfiles);
    }
    
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<SpringBeanPointer<?>> getLocalBeans() {
        PsiClass psiClass = this.getConfig();
        Collection<SpringBeanPointer<?>> returnSet = new HashSet<>();
        NotNullLazyValue<Set<SpringBeanPointer<?>>> mapperBeanPointer = NotNullLazyValue.lazy(() -> {
            BeanService beanService = BeanService.getInstance();
            Set<SpringBeanPointer<?>> pointers = new LinkedHashSet();
            
            if (psiClass.isValid()) {
                pointers.add(beanService.createSpringBeanPointer(new CustomSpringComponent(psiClass)));
            }
            return pointers;
        });
        ContainerUtil.addAllNotNull(returnSet, mapperBeanPointer.getValue());
        return returnSet;
    }
}

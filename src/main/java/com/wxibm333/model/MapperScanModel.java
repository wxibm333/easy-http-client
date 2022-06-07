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
import com.intellij.psi.PsiElementRef;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.ref.AnnotationChildLink;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.spring.contexts.model.CacheableCommonSpringModel;
import com.intellij.spring.contexts.model.LocalAnnotationModel;
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
    
    public List<PsiPackage> getScanPackages(@NotNull PsiClass psiClass) {
        List<PsiPackage> psiPackages = new ArrayList<>();
        PsiAnnotation annotation = AnnotationUtil.findAnnotation(psiClass, AnnotationNameConstant.MAPPER_SCAN);
        if (Objects.nonNull(annotation)) {
            List<String> packagesNameList = AnnoUtil.getAnnotationAttrValue(annotation, "value");
            for (String packagesName : packagesNameList) {
                PsiPackage psiPackage = JavaPsiFacade.getInstance(this.getModule()
                                .getProject())
                        .findPackage(packagesName);
                psiPackages.add(psiPackage);
            }
            
        }
        return psiPackages;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Set<PsiPackage> getMapperInterfacePackage() {
        Set<PsiPackage> returnList = new HashSet<>();
        JamStringAttributeMeta.Collection<Collection<PsiPackage>> attributeMeta = new JamStringAttributeMeta.Collection(
                "value", new PackageJamConverter());
        AnnotationChildLink annotationChildLink = new AnnotationChildLink(AnnotationNameConstant.MAPPER_SCAN);
        PsiClass configPsiClass = this.getConfig();
        PsiElementRef<PsiAnnotation> childRef = annotationChildLink.createChildRef(configPsiClass);
        for (JamStringAttributeElement<Collection<PsiPackage>> element : attributeMeta.getJam(childRef)) {
            if (element != null) {
                Collection<PsiPackage> psiPackages = element.getValue();
                if (psiPackages != null) {
                    ContainerUtil.addAllNotNull(returnList, psiPackages);
                }
            }
        }
        return returnList;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Collection<SpringBeanPointer<?>> getLocalBeans() {
        List<PsiPackage> scanPackagesList = this.getScanPackages(this.getConfig());
        Set<PsiPackage> mapperInterfaceList = this.getMapperInterfacePackage();
        for (PsiPackage psiClass : mapperInterfaceList) {
            System.out.println(psiClass.getQualifiedName());
        }
        //        spring 模型管理
        //        SpringManager instance = SpringManager.getInstance(null);
        //        SpringModel combinedModel = instance.getCombinedModel(module);
        //        Set<SpringModel> dependencies = combinedModel.getDependencies();
        //        dependencies.add(null);
        //        combinedModel.setDependencies(dependencies);
        PsiClass psiClass = JavaPsiFacade.getInstance(this.getModule()
                        .getProject())
                .findClass("cn.cloudlizard.file.mapper.FileInfoMapper", GlobalSearchScope.allScope(this.getModule()
                        .getProject()));
        NotNullLazyValue<Set<SpringBeanPointer<?>>> fileInfoMapper = NotNullLazyValue.lazy(() -> {
            BeanService beanService = BeanService.getInstance();
            Set<SpringBeanPointer<?>> pointers = new LinkedHashSet();
            
            if (psiClass.isValid() && Objects.nonNull(psiClass.getQualifiedName()) && psiClass.getQualifiedName()
                    .contains("FileInfoMapper")) {
                pointers.add(beanService.createSpringBeanPointer(new CustomSpringComponent(psiClass)));
            }
            
            return pointers;
        });
        return fileInfoMapper.getValue();
    }
}

package com.wxibm333.model;

import com.intellij.jam.JamStringAttributeElement;
import com.intellij.jam.reflect.JamStringAttributeMeta;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementRef;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.ref.AnnotationChildLink;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.AnnotatedElementsSearch;
import com.intellij.spring.contexts.model.LocalModel;
import com.intellij.spring.model.CommonSpringBean;
import com.intellij.spring.model.custom.CustomLocalComponentsDiscoverer;
import com.intellij.spring.model.jam.converters.PackageJamConverter;
import com.intellij.spring.model.jam.stereotype.CustomSpringComponent;
import com.intellij.spring.model.utils.SpringCommonUtils;
import com.intellij.util.containers.ContainerUtil;
import com.wxibm333.constant.AnnotationNameConstant;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 请参考 {@link com.intellij.spring.model.extensions.myBatis.SpringMyBatisBeansProvider}
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-10 10:17 AM
 */
public class SpringMapperScanBeansProvider extends CustomLocalComponentsDiscoverer {
    
    /**
     * 通过配置包名获取Mapper的扫描的包名
     *
     * @param configPsiClass spring boot 中带有@MapperScan注解的配置类
     * @return java.util.Set<com.intellij.psi.PsiPackage>
     * @author wangxin
     * @date 2022-06-13 1:46 PM
     */
    public Set<PsiPackage> getMapperInterfacePackage(PsiClass configPsiClass) {
        Set<PsiPackage> returnList = new HashSet<>();
        JamStringAttributeMeta.Collection<Collection<PsiPackage>> attributeMeta = new JamStringAttributeMeta.Collection(
                "value", new PackageJamConverter());
        AnnotationChildLink annotationChildLink = new AnnotationChildLink(AnnotationNameConstant.MAPPER_SCAN);
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
    
    /**
     * 递归扫描mapper接口，并加入myBatisMappers集合中
     *
     * @param scope          查询作用域
     * @param psiPackage     需处理的包
     * @param myBatisMappers 收集的mapper集合
     * @author wangxin
     * @date 2022-06-14 9:42 AM
     */
    private static void processBasePackage(GlobalSearchScope scope, PsiPackage psiPackage,
            Collection<CommonSpringBean> myBatisMappers) {
        PsiClass[] psiClassArray = psiPackage.getClasses(scope);
        for (PsiClass psiClass : psiClassArray) {
            if (psiClass.isInterface()) {
                myBatisMappers.add(new CustomSpringComponent(psiClass));
            }
        }
        
        PsiPackage[] subPackages = psiPackage.getSubPackages(scope);
        for (PsiPackage subPackage : subPackages) {
            processBasePackage(scope, subPackage, myBatisMappers);
        }
    }
    
    @Override
    public @NotNull Collection<CommonSpringBean> getCustomComponents(@NotNull LocalModel localModel) {
        Module module = localModel.getModule();
        Collection<CommonSpringBean> myBatisMappers = new HashSet();
        if (Objects.nonNull(module)) {
            Project project = module.getProject();
            GlobalSearchScope globalSearchScope = GlobalSearchScope.allScope(project);
            // 项目(module和library)中MapperScan注解PsiClass对象
            PsiClass mapperScanAnnotationClass = SpringCommonUtils.findLibraryClass(module,
                    AnnotationNameConstant.MAPPER_SCAN);
            if (Objects.nonNull(mapperScanAnnotationClass)) {
                // 通过注解查找配置使用的类
                Collection<PsiClass> psiClasses = AnnotatedElementsSearch.searchPsiClasses(mapperScanAnnotationClass,
                                globalSearchScope)
                        .findAll();
                // 查找mapper接口
                for (PsiClass configMapperScanClass : psiClasses) {
                    Set<PsiPackage> mapperInterfacePackage = this.getMapperInterfacePackage(configMapperScanClass);
                    for (PsiPackage psiPackage : mapperInterfacePackage) {
                        processBasePackage(globalSearchScope, psiPackage, myBatisMappers);
                    }
                }
            }
        }
        return myBatisMappers;
    }
}

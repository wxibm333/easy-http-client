package com.wxibm333.util;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiArrayInitializerMemberValue;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-06 3:45 PM
 */
public class AnnotationUtil {
    
    /**
     * 获取注解的属性值
     *
     * @param annotation java 注解
     * @param attrName   属性名称
     * @return java.util.List<java.lang.String>
     * @author wangxin
     * @date 2022-06-06 3:48 PM
     */
    public static List<String> getAnnotationAttrValue(@NotNull PsiAnnotation annotation, @NotNull String attrName) {
        //一个value class com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl
        //多个value  class com.intellij.psi.impl.source.tree.java.PsiArrayInitializerMemberValueImpl
        PsiAnnotationMemberValue attributeValue = annotation.findDeclaredAttributeValue(attrName);
        List<String> returnList = new ArrayList<>();
        if (attributeValue instanceof PsiLiteralExpression) {
            String text = attributeValue.getText();
            if (!StrUtil.isBlank(text)) {
                returnList.add(text);
            }
        }
        
        if (attributeValue instanceof PsiArrayInitializerMemberValue) {
            PsiAnnotationMemberValue[] initializers = ((PsiArrayInitializerMemberValue) attributeValue).getInitializers();
            for (PsiAnnotationMemberValue initializer : initializers) {
                String text = initializer.getText();
                if (!StrUtil.isBlank(text)) {
                    returnList.add(text);
                }
            }
        }
        return returnList;
    }
}

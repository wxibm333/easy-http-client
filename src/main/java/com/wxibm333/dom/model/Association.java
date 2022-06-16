package com.wxibm333.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.wxibm333.dom.converter.AliasConverter;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:04 AM
 * @version v1.0.0
 */
public interface Association extends GroupFour, ResultMapGroup, PropertyGroup {

    /**
     * Gets java type.
     *
     * @return the java type
     */
    @NotNull
    @Attribute("javaType")
    @Convert(AliasConverter.class)
    GenericAttributeValue<PsiClass> getJavaType();
}

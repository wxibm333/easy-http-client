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
 * @date  2022-06-16 11:05 AM
 * @version v1.0.0
 */
public interface Collection extends GroupFour, ResultMapGroup, PropertyGroup {

    /**
     * Gets of type.
     *
     * @return the of type
     */
    @NotNull
    @Attribute("ofType")
    @Convert(AliasConverter.class)
    GenericAttributeValue<PsiClass> getOfType();

}

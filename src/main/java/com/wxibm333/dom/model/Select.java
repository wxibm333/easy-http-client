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
 * @date  2022-06-16 11:10 AM
 * @version v1.0.0
 */
public interface Select extends GroupTwo, ResultMapGroup, IdDomElement  {

    /**
     * select 标签对应的 resultType
     * @return the result type
     */
    @NotNull
    @Attribute("resultType")
    @Convert(AliasConverter.class)
    GenericAttributeValue<PsiClass> getResultType();

}

package com.wxibm333.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.wxibm333.dom.converter.AliasConverter;
import com.wxibm333.dom.converter.ResultMapConverter;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:09 AM
 * @version v1.0.0
 */
public interface ResultMap extends GroupFour, IdDomElement {

    /**
     * Gets extends.
     *
     * @return the extends
     */
    @NotNull
    @Attribute("extends")
    @Convert(ResultMapConverter.class)
    GenericAttributeValue<XmlAttributeValue> getExtends();

    /**
     * Gets type.
     *
     * @return the type
     */
    @NotNull
    @Attribute("type")
    @Convert(AliasConverter.class)
    GenericAttributeValue<PsiClass> getType();

}

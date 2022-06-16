package com.wxibm333.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.SubTagList;
import com.wxibm333.dom.converter.AliasConverter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:08 AM
 * @version v1.0.0
 */
public interface ParameterMap extends IdDomElement {

    /**
     * Gets type.
     *
     * @return the type
     */
    @NotNull
    @Attribute("type")
    @Convert(AliasConverter.class)
    GenericAttributeValue<PsiClass> getType();

    /**
     * Gets parameters.
     *
     * @return the parameters
     */
    @SubTagList("parameter")
    List<Parameter> getParameters();

}

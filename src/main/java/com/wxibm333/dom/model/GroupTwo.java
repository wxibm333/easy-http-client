package com.wxibm333.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.SubTagList;
import com.wxibm333.dom.converter.AliasConverter;
import com.wxibm333.dom.converter.ParameterMapConverter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:06 AM
 * @version v1.0.0
 */
public interface GroupTwo extends GroupOne {

    /**
     * Gets binds.
     *
     * @return the binds
     */
    @SubTagList("bind")
    List<Bind> getBinds();

    /**
     * Gets parameter map.
     *
     * @return the parameter map
     */
    @NotNull
    @Attribute("parameterMap")
    @Convert(ParameterMapConverter.class)
    GenericAttributeValue<XmlTag> getParameterMap();

    /**
     * Gets parameter type.
     *
     * @return the parameter type
     */
    @NotNull
    @Attribute("parameterType")
    @Convert(AliasConverter.class)
    GenericAttributeValue<PsiClass> getParameterType();
}

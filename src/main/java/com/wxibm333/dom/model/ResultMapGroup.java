package com.wxibm333.dom.model;

import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.wxibm333.dom.converter.ResultMapConverter;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:10 AM
 * @version v1.0.0
 */
public interface ResultMapGroup extends DomElement {

    /**
     * Gets result map.
     *
     * @return the result map
     */
    @NotNull
    @Attribute("resultMap")
    @Convert(ResultMapConverter.class)
    GenericAttributeValue<XmlTag> getResultMap();
}

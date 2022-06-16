package com.wxibm333.dom.model;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;
import com.wxibm333.dom.converter.DaoMethodConverter;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:07 AM
 * @version v1.0.0
 */
public interface IdDomElement extends DomElement {

    /**
     * Gets id.
     *
     * @return the id
     */
    @Required
    @NameValue
    @Attribute("id")
    @Convert(DaoMethodConverter.class)
    GenericAttributeValue<Object> getId();

    /**
     * Sets value.
     *
     * @param content the content
     */
    void setValue(String content);
}

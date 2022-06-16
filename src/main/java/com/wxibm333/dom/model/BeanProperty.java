package com.wxibm333.dom.model;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:04 AM
 * @version v1.0.0
 */
public interface BeanProperty extends DomElement {

    /**
     * Gets name.
     *
     * @return the name
     */
    @NotNull
    @Attribute("name")
    GenericAttributeValue<String> getName();

    /**
     * Gets value.
     *
     * @return the value
     */
    @NotNull
    @Attribute("value")
    GenericAttributeValue<String> getValue();
}

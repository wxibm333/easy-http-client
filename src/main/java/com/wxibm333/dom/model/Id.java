package com.wxibm333.dom.model;

import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.wxibm333.dom.converter.PropertyConverter;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:07 AM
 * @version v1.0.0
 */
public interface Id extends PropertyGroup {

    @Override
    @Attribute("property")
    @Convert(PropertyConverter.class)
    GenericAttributeValue<XmlAttributeValue> getProperty();

}

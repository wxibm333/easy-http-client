package com.wxibm333.dom.model;

import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.wxibm333.dom.converter.SqlConverter;


/**
 *
 * @author wangXin
 * @date  2022-06-16 11:07 AM
 * @version v1.0.0
 */
public interface Include extends DomElement {

    /**
     * Gets ref id.
     *
     * @return the ref id
     */
    @Attribute("refid")
    @Convert(SqlConverter.class)
    GenericAttributeValue<XmlTag> getRefId();

}

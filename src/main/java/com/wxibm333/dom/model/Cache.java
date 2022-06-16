package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:04 AM
 * @version v1.0.0
 */
public interface Cache extends DomElement {

    /**
     * Gets properties.
     *
     * @return the properties
     */
    @SubTagList("property")
    List<Property> getProperties();

}

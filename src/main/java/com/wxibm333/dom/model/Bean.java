package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:04 AM
 * @version v1.0.0
 */
public interface Bean extends DomElement {

    /**
     * Gets bean properties.
     *
     * @return the bean properties
     */
    @NotNull
    @SubTagList("property")
    List<BeanProperty> getBeanProperties();

}

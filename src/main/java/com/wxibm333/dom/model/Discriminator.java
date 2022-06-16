package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Required;
import com.intellij.util.xml.SubTagList;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:05 AM
 * @version v1.0.0
 */
public interface Discriminator extends DomElement {

    /**
     * Gets cases.
     *
     * @return the cases
     */
    @Required
    @SubTagList("case")
    List<Case> getCases();

}

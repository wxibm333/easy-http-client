package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Required;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 *
 * @author wangXin
 * @date  2022-06-16 11:05 AM
 * @version v1.0.0
 */
public interface Choose extends DomElement {

    /**
     * Gets whens.
     *
     * @return the whens
     */
    @NotNull
    @Required
    @SubTagList("when")
    List<When> getWhens();

    /**
     * Gets otherwise.
     *
     * @return the otherwise
     */
    @SubTag("otherwise")
    Otherwise getOtherwise();

}

package com.wxibm333.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:11 AM
 * @version v1.0.0
 */
public interface TypeAlias extends DomElement {

    /**
     * Gets type.
     *
     * @return the type
     */
    @NotNull
    @Attribute("type")
    GenericAttributeValue<PsiClass> getType();

    /**
     * Gets alias.
     *
     * @return the alias
     */
    @NotNull
    @Attribute("alias")
    GenericAttributeValue<String> getAlias();

}

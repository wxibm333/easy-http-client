package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Namespace;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 *
 * @author wangXin
 * @date  2022-06-16 11:05 AM
 * @version v1.0.0
 */
@Namespace("MybatisConfiguration")
public interface Configuration extends DomElement {

    /**
     * Gets type aliases.
     *
     * @return the type aliases
     */
    @NotNull
    @SubTagList("typeAliases")
    List<TypeAliases> getTypeAliases();

}

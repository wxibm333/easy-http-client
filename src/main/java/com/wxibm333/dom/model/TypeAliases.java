package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:11 AM
 * @version v1.0.0
 */
public interface TypeAliases extends DomElement {

    /**
     * Gets type alias.
     *
     * @return the type alias
     */
    @NotNull
    @SubTagList("typeAlias")
    List<TypeAlias> getTypeAlias();

    /**
     * Gets packages.
     *
     * @return the packages
     */
    @NotNull
    @SubTagList("package")
    List<Package> getPackages();

}

package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:05 AM
 * @version v1.0.0
 */
public interface Constructor extends DomElement {

    /**
     * Gets args.
     *
     * @return the args
     */
    @SubTagList("arg")
    List<Arg> getArgs();

    /**
     * Gets id args.
     *
     * @return the id args
     */
    @SubTagList("idArg")
    List<IdArg> getIdArgs();
}

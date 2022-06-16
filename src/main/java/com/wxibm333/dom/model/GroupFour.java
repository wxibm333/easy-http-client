package com.wxibm333.dom.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;

import java.util.List;

/**
 *
 * @author wangXin
 * @date  2022-06-16 11:06 AM
 * @version v1.0.0
 */
public interface GroupFour extends DomElement {

    /**
     * Gets constructor.
     *
     * @return the constructor
     */
    @SubTag("constructor")
    Constructor getConstructor();

    /**
     * Gets ids.
     *
     * @return the ids
     */
    @SubTagList("id")
    List<Id> getIds();

    /**
     * Gets results.
     *
     * @return the results
     */
    @SubTagList("result")
    List<Result> getResults();

    /**
     * Gets associations.
     *
     * @return the associations
     */
    @SubTagList("association")
    List<Association> getAssociations();

    /**
     * Gets collections.
     *
     * @return the collections
     */
    @SubTagList("collection")
    List<Collection> getCollections();

    /**
     * Gets discriminator.
     *
     * @return the discriminator
     */
    @SubTag("discriminator")
    Discriminator getDiscriminator();
}

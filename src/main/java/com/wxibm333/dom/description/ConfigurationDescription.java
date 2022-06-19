package com.wxibm333.dom.description;

import com.intellij.util.xml.DomFileDescription;
import com.wxibm333.dom.model.Configuration;

/**
 * The type Configuration description.
 *
 * @author yanglin
 */
public class ConfigurationDescription extends DomFileDescription<Configuration> {

    /**
     * Instantiates a new Configuration description.
     */
    public ConfigurationDescription() {
        super(Configuration.class, "configuration");
    }

    @Override
    protected void initializeFileDescription() {
        registerNamespacePolicy("MybatisConfiguration", "-//mybatis.org//DTD Config 3.0//EN", "http://mybatis.org/dtd/mybatis-3-config.dtd");
    }
}

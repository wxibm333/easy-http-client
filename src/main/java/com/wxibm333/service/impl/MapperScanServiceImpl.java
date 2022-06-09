package com.wxibm333.service.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.module.Module;
import com.intellij.spring.SpringManager;
import com.intellij.spring.contexts.model.SpringModel;
import com.wxibm333.model.MapperScanSpringModel;
import com.wxibm333.service.AbstractMapperScanService;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-04 8:34 AM
 */
public class MapperScanServiceImpl extends AbstractMapperScanService {
    
    @Override
    public void addMapperScanModel(@NotNull Project project, @NotNull Module module) {
        SpringManager springManager = SpringManager.getInstance(project);
        SpringModel combinedModel = springManager.getCombinedModel(module);
        Set<SpringModel> dependencies = combinedModel.getDependencies();
        dependencies.add(new MapperScanSpringModel(module));
        combinedModel.setDependencies(dependencies);
    }
}

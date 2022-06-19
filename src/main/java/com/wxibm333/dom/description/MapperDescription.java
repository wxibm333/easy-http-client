package com.wxibm333.dom.description;

import com.intellij.util.xml.DomFileDescription;
import com.wxibm333.dom.model.Mapper;

/**
 * <p>
 * mapper.xml 文件属性提示
 * </p>
 *
 * @author wangXin
 * @version v1.0.0
 * @date 2022-06-16 11:03 AM
 */
public class MapperDescription extends DomFileDescription<Mapper> {
    
    public static final String[] HTTP_MYBATIS_ORG_DTD_MYBATIS_3_MAPPER_DTD = new String[] {
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd", "http://www.mybatis.org/dtd/mybatis-3-mapper.dtd"};
    
    /**
     * Instantiates a new Mapper description.
     */
    public MapperDescription() {
        super(Mapper.class, "mapper");
    }
    
    @Override
    protected void initializeFileDescription() {
        registerNamespacePolicy("MybatisXml", HTTP_MYBATIS_ORG_DTD_MYBATIS_3_MAPPER_DTD);
    }
}

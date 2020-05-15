package com.wxibm333.util;

import com.google.common.collect.Sets;
import com.intellij.lang.jvm.annotation.JvmAnnotationArrayValue;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttribute;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttributeValue;
import com.intellij.lang.jvm.annotation.JvmAnnotationConstantValue;
import com.intellij.psi.PsiAnnotation;
import com.wxibm333.constant.Common;
import com.wxibm333.constant.SpringClassNames;
import java.util.List;
import java.util.Set;

/**
 * @author wangXin
 * @version v1.0.0
 * @date 2020-05-15 14:30
 */
public class AnnotationUtil {

  public static Set<String> getPathByRequestMappingAnnotation(PsiAnnotation annotation) {
    if (SpringClassNames.REQUEST_COLLECTION.contains(annotation.getQualifiedName())) {
      Set<String> pathSet = Sets.newHashSet();
      List<JvmAnnotationAttribute> attributes = annotation.getAttributes();
      for (JvmAnnotationAttribute attribute : attributes) {
        String attributeName = attribute.getAttributeName();
        if (Common.PATH.equals(attributeName) || Common.VALUE.equals(attributeName)) {
          JvmAnnotationAttributeValue attributeValue = attribute.getAttributeValue();
          if (attributeValue instanceof JvmAnnotationConstantValue) {
            String path = (String) ((JvmAnnotationConstantValue) attributeValue)
                .getConstantValue();
            pathSet.add(path);
          } else if (attributeValue instanceof JvmAnnotationArrayValue) {
            List<JvmAnnotationAttributeValue> values = ((JvmAnnotationArrayValue) attributeValue)
                .getValues();
            for (JvmAnnotationAttributeValue value : values) {
              String classPath = (String) ((JvmAnnotationConstantValue) value)
                  .getConstantValue();
              pathSet.add(classPath);
            }
          }
        }
      }
      return pathSet;
    }
    return null;
  }
}

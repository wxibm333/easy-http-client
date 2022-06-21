package com.wxibm333.dom.converter;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.CustomReferenceConverter;
import com.intellij.util.xml.DomJavaUtil;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.PsiClassConverter;
import com.wxibm333.alias.AliasClassReference;
import com.wxibm333.alias.AliasFacade;
import com.wxibm333.constant.StrConstant;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The type Alias converter.
 *
 * @author yanglin
 */
public class AliasConverter extends ConverterAdaptor<PsiClass> implements CustomReferenceConverter<PsiClass> {

    private final PsiClassConverter delegate = new PsiClassConverter();

    @Nullable
    @Override
    public PsiClass fromString(@Nullable @NonNls String s, ConvertContext context) {
        if (StringUtil.isEmptyOrSpaces(s)) {
            return null;
        }

        // 识别别名
        if (!s.contains(StrConstant.DOT)) {
            return AliasFacade.getInstance(context.getProject())
                .findPsiClass(context.getXmlElement(), s)
                .orElse(null);
        }
        // 根据全程查找
        return DomJavaUtil.findClass(s, context.getFile(), context.getModule(), GlobalSearchScope.allScope(context.getProject()));
    }


    @Nullable
    @Override
    public String toString(@Nullable PsiClass psiClass, ConvertContext context) {
        return delegate.toString(psiClass, context);
    }

    @NotNull
    @Override
    public PsiReference @NotNull [] createReferences(GenericDomValue<PsiClass> value, PsiElement element, ConvertContext context) {
        // 如果是PsiClass的全名，使用类转换器创建PsiReference
        if (notAlias((XmlAttributeValue) element)) {
            return delegate.createReferences(value, element, context);
        } else {
            return new PsiReference[]{new AliasClassReference((XmlAttributeValue) element)};
        }
    }

    private boolean notAlias(XmlAttributeValue element) {
        return element.getValue().contains(StrConstant.DOT);
    }


}

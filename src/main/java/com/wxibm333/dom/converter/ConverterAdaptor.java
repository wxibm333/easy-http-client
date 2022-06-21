package com.wxibm333.dom.converter;

import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;


/**
 * mybatis 转换适配器接口
 * @author wangXin
 * @date  2022-06-17 10:36 AM
 * @version v1.0.0
 */
public abstract class ConverterAdaptor<T> extends ResolvingConverter<T> {

    @NotNull
    @Override
    public Collection<? extends T> getVariants(ConvertContext context) {
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public String toString(@Nullable T t, ConvertContext context) {
//    throw new UnsupportedOperationException();
        return null;
    }

    @Nullable
    @Override
    public T fromString(@Nullable @NonNls String s, ConvertContext context) {
        return null;
    }
}

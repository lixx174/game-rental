package com.jinx.application.factory;

import java.util.function.Consumer;

/**
 * @author Jinx
 * @deprecated 好像没有啥用 构造得有参数吧 你只提供个回调 咋给你create
 */
@Deprecated
public interface Factory<T> {

    T create(Consumer<T> callback);
}

package com.jinx.domain.factory;

import java.util.function.Consumer;

/**
 * @author Jinx
 */
public interface Factory {

    <T> T create(Consumer<T> callback);
}

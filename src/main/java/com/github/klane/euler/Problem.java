package com.github.klane.euler;

import java.util.function.Supplier;

public interface Problem<T extends Number> extends Supplier<T> {

    boolean exists();
}

package com.github.klane.euler;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface Problem<T extends Number> extends IntSupplier, Supplier<T> { }

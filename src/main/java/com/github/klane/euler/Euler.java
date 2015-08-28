package com.github.klane.euler;

import com.google.inject.Guice;
import com.google.inject.Inject;

public final class Euler {

    private final Runnable application;

    @Inject
    public Euler(final Runnable application) {
        this.application = application;
    }

    public static void main(String[] args) {
        Guice.createInjector(new ApplicationModule()).getInstance(Euler.class).application.run();
    }
}

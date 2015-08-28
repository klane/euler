package com.github.klane.euler;

import com.google.common.base.Ticker;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;

public final class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TimeLimiter.class).toInstance(new SimpleTimeLimiter());
        bind(Ticker.class).toInstance(Ticker.systemTicker());
        bind(PrintStream.class).toInstance(System.out);
        bind(Scanner.class).toInstance(new Scanner(System.in));
        bind(new TypeLiteral<Consumer<Problem<?>>>(){}).to(ProblemRunner.class);
        bind(Runnable.class).to(Application.class);
    }
}

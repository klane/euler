package com.github.klane.euler;

import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.base.Ticker;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.google.inject.Inject;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public final class ProblemRunner implements Consumer<Problem<?>> {

    private static final int TIME_LIMIT = 1;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
    private static final TimeUnit LIMIT_UNIT = TimeUnit.MINUTES;

    private final TimeLimiter timeLimiter;
    private final Stopwatch stopwatch;
    private final PrintStream output;
    private final Function<Problem<?>, Number> solver;

    @Inject
    public ProblemRunner(final TimeLimiter timeLimiter, final Ticker ticker, final PrintStream output) {
        this.timeLimiter = timeLimiter;
        this.stopwatch = Stopwatch.createUnstarted(ticker);
        this.output = output;
        this.solver = problem -> {
            this.stopwatch.start();
            Number result = problem.get();
            this.stopwatch.stop();
            return result;
        };
    }

    @Override
    public void accept(final Problem<?> problem) {
        Problem<?> timeLimitedProblem = this.timeLimiter.newProxy(problem, Problem.class, TIME_LIMIT, LIMIT_UNIT);
        this.output.printf("\nRunning %s...\n", timeLimitedProblem);

        try {
            this.output.println("\tResult: " + this.solver.apply(timeLimitedProblem));
            this.output.printf("\tTook %d %s to solve.\n\n", this.stopwatch.elapsed(UNIT), UNIT.name().toLowerCase());
        } catch (UncheckedTimeoutException e) {
            this.output.printf("\tFailed to get a result in %d %s.\n\n", TIME_LIMIT, LIMIT_UNIT.name().toLowerCase());
        } catch (Exception e) {
            throw Throwables.propagate(e);
        } finally {
            this.stopwatch.reset();
        }
    }
}

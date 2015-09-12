package com.github.klane.euler;

import com.google.common.base.Throwables;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;

public final class RProblem extends AbstractProblem<Integer> {

    public static final REngine ENGINE;

    static {
        try {
            ENGINE = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine");
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public RProblem(final int id) {
        super(Languages.R, id);
    }

    @Override
    public Integer get() {
        try {
            ENGINE.parseAndEval(String.format("source('%s')", super.file));
            return ENGINE.parseAndEval("solve()").asInteger();
        } catch (REngineException | REXPMismatchException e) {
            throw Throwables.propagate(e);
        }
    }
}

package com.github.klane.euler;

import com.google.common.base.Throwables;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;

public final class RProblem extends AbstractProblem<Integer> {

    public static REngine ENGINE;

    static {
        try {
            ENGINE = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine");
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public RProblem(final int id) {
        super(Languages.R);
        super.setId(id);
    }

    @Override
    public Integer get() {
        try {
            ENGINE.parseAndEval(String.format("source('%s/Problem%d.R')", Languages.R.get(), super.getAsInt()));
            return ENGINE.parseAndEval("solve()").asInteger();
        } catch (REngineException | REXPMismatchException e) {
            throw Throwables.propagate(e);
        }
    }
}

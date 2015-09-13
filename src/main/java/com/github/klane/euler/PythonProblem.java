package com.github.klane.euler;

import com.google.common.base.Throwables;
import jep.Jep;
import jep.JepException;

import java.io.File;

public final class PythonProblem extends AbstractProblem<Integer> {

    public static final Jep INTERPRETER;
    private static File PREVIOUS_PROBLEM;

    static {
        try {
            INTERPRETER = new Jep(false);
            INTERPRETER.eval("import sys");
        } catch (JepException e) {
            throw Throwables.propagate(e);
        }
    }

    public PythonProblem(final int id) {
        super(Language.PYTHON, id);

        if (super.exists()) {
            try {
                if (PREVIOUS_PROBLEM != null) {
                    INTERPRETER.eval(String.format("sys.path.remove('%s')", PREVIOUS_PROBLEM.getParent()));
                }

                INTERPRETER.eval(String.format("sys.path.append('%s')", super.file.getParent()));
                INTERPRETER.eval(String.format("from Problem%d import solve", id));
            } catch (JepException e) {
                throw Throwables.propagate(e);
            } finally {
                PREVIOUS_PROBLEM = super.file;
            }
        }
    }

    @Override
    public Integer get() {
        try {
            return (Integer) INTERPRETER.invoke("solve");
        } catch (JepException e) {
            throw Throwables.propagate(e);
        }
    }
}

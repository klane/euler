package com.github.klane.euler;

import com.google.common.base.Throwables;
import jep.Jep;
import jep.JepException;

public final class PythonProblem extends AbstractProblem<Integer> {

    public static Jep INTERPRETER;

    static {
        try {
            INTERPRETER = new Jep(false);
            INTERPRETER.eval("import sys");
            INTERPRETER.eval(String.format("sys.path.insert(0, '%s')", Languages.PYTHON.get()));
        } catch (JepException e) {
            throw Throwables.propagate(e);
        }
    }

    public PythonProblem(final int id) {
        super(Languages.PYTHON);
        super.setId(id);

        try {
            INTERPRETER.eval(String.format("from Problem%d import solve", id));
        } catch (JepException e) {
            throw Throwables.propagate(e);
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

package com.github.klane.euler;

import com.google.common.base.Throwables;

import java.io.File;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public enum Languages implements IntFunction<Problem<?>>, Supplier<String> {

    JAVA("com.github.klane.euler.problems") {
        @Override
        public Problem<?> apply(final int id) {
            try {
                Class<?> problemClass = Class.forName(this.get() + ".Problem" + id);
                return (Problem<?>) problemClass.newInstance();
            } catch (ClassNotFoundException e) {
                return null;
            } catch (IllegalAccessException | InstantiationException e) {
                throw Throwables.propagate(e);
            }
        }
    },

    PYTHON("src/main/python") {
        @Override
        public Problem<?> apply(final int id) {
            String file = String.format("%s/Problem%d.py", this.get(), id);
            return new File(file).exists() ? new PythonProblem(id) : null;
        }
    },

    R("src/main/r") {
        @Override
        public Problem<?> apply(final int id) {
            String file = String.format("%s/Problem%d.R", this.get(), id);
            return new File(file).exists() ? new RProblem(id) : null;
        }
    };

    private final String problemLocation;

    Languages(final String problemLocation) {
        this.problemLocation = problemLocation;
    }

    @Override
    public String get() {
        return this.problemLocation;
    }
}

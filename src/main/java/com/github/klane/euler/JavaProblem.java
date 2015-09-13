package com.github.klane.euler;

import com.google.common.base.Throwables;

import java.io.File;

public class JavaProblem<T extends Number> extends AbstractProblem<T> {

    private Problem<T> problem;

    public JavaProblem() {
        super(Language.JAVA);
    }

    @SuppressWarnings("unchecked")
    public JavaProblem(final int id) {
        super(Language.JAVA, id);

        try {
            String path = super.file.getParent().replace(File.separator, ".").replace("src.main.java.", "");
            this.problem = (Problem<T>) Class.forName(String.format("%s.Problem%d", path, id)).newInstance();
        } catch (ClassNotFoundException e) {
            this.problem = null;
        } catch (IllegalAccessException | InstantiationException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public T get() {
        return this.problem.get();
    }
}

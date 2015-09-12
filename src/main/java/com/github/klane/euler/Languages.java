package com.github.klane.euler;

import java.util.function.IntFunction;

public enum Languages implements IntFunction<Problem<?>> {

    JAVA(JavaProblem::new, ".java", "src", "main", "java", "com", "github", "klane", "euler", "problems"),
    PYTHON(PythonProblem::new, ".py", "src", "main", "python"),
    R(RProblem::new, ".R", "src", "main", "r");

    private final String extension;
    private final String[] location;
    private final IntFunction<Problem<?>> function;

    Languages(final IntFunction<Problem<?>> function, final String extension, final String... location) {
        this.function = function;
        this.extension = extension;
        this.location = location;
    }

    @Override
    public Problem<?> apply(int id) {
        return this.function.apply(id);
    }

    public String getExtension() {
        return this.extension;
    }

    public String[] getLocation() {
        return this.location;
    }
}

package com.github.klane.euler;

public abstract class JavaProblem<T extends Number> extends AbstractProblem<T> {

    public JavaProblem() {
        super(Languages.JAVA);
        super.setId(Integer.parseInt(this.getClass().getSimpleName().replace("Problem", "")));
    }
}

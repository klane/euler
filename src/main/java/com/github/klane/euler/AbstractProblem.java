package com.github.klane.euler;

public abstract class AbstractProblem<T extends Number> implements Problem<T> {

    private int id;
    private final Languages language;

    public AbstractProblem(final Languages language) {
        this.language = language;
    }

    protected void setId(final int id) {
        this.id = id;
    }

    @Override
    public int getAsInt() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.language + " problem " + this.id;
    }
}

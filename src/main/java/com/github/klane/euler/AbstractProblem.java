package com.github.klane.euler;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;

import java.io.File;

public abstract class AbstractProblem<T extends Number> implements Problem<T> {

    private final Language language;
    private final int id;
    protected final File file;

    public AbstractProblem(final Language language) {
        this(language, null);
    }

    public AbstractProblem(final Language language, final int id) {
        this(language, (Integer) id);
    }

    private AbstractProblem(final Language language, final Integer id) {
        this.language = language;
        int problemsPerPackage = 20;
        Joiner joiner = Joiner.on(File.separator);
        Range<Integer> range = Range.closed(1, problemsPerPackage);

        if (id == null) {
            this.id = Integer.parseInt(this.getClass().getSimpleName().replace("Problem", ""));
        } else {
            Preconditions.checkArgument(id > 0, "Problem id must be positive");
            this.id = id;
        }

        while (!range.contains(this.id)) {
            range = Range.closed(range.upperEndpoint() + 1, range.upperEndpoint() + problemsPerPackage);
        }

        this.file = new File(joiner.join(joiner.join(this.language.getLocation()),
                String.format("problems%03d_%03d", range.lowerEndpoint(), range.upperEndpoint()),
                "Problem" + this.id + this.language.getExtension()));
    }

    @Override
    public boolean exists() {
        return this.file.exists();
    }

    @Override
    public String toString() {
        return this.language + " problem " + this.id;
    }
}

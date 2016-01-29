package com.github.klane.euler;

import com.google.common.base.Throwables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    public List<String> readFile() {
        Path path = Paths.get("src", "main", "resources", String.format("problem%d.txt", super.id));

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("\tFile not found: " + path);
            return null;
        }
    }

    @Override
    public T get() {
        return this.problem.get();
    }
}

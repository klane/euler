package com.github.klane.euler;

import com.google.inject.Inject;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Application implements Runnable {

    private final PrintStream output;
    private final Scanner scanner;
    private final Consumer<Problem<?>> problemRunner;

    @Inject
    public Application(final PrintStream output, final Scanner scanner, final Consumer<Problem<?>> problemRunner) {
        this.output = output;
        this.scanner = scanner;
        this.problemRunner = problemRunner;
    }

    @Override
    public void run() {
        String languageNames = Arrays.toString(Language.values());

        Supplier<Language> languageSupplier = () -> {
            Language language = null;

            while (language == null) {
                this.output.printf("Enter language %s: ", languageNames);

                try {
                    language = Language.valueOf(this.scanner.next().toUpperCase());
                } catch (IllegalArgumentException e) {
                    this.output.println("Unsupported language");
                }
            }

            return language;
        };

        Function<Language, String> inputFunction = language -> {
            this.output.printf("Enter %s problem number, c to change language, or q to quit: ", language);
            return this.scanner.next();
        };

        Language language = languageSupplier.get();
        String input = inputFunction.apply(language);

        while (!input.equalsIgnoreCase("q")) {
            if (input.equalsIgnoreCase("c")) {
                language = languageSupplier.get();
            } else {
                try {
                    Problem<?> problem = language.apply(Integer.parseInt(input));

                    if (problem.exists()) {
                        this.problemRunner.accept(problem);
                    } else {
                        this.output.printf("%s not found\n", problem);
                    }
                } catch (IllegalArgumentException e) {
                    this.output.println("Invalid entry");
                }
            }

            input = inputFunction.apply(language);
        }

        PythonProblem.INTERPRETER.close();
        RProblem.ENGINE.close();
        System.exit(0);
    }
}

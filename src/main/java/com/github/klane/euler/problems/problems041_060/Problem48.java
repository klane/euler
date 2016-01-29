package com.github.klane.euler.problems.problems041_060;

import com.github.klane.euler.JavaProblem;

import java.math.BigInteger;

public final class Problem48 extends JavaProblem<BigInteger> {

    public static final int NUM_DIGITS = 10;
    public static final int LIMIT = 1000;

    @Override
    public BigInteger get() {
        BigInteger sum = BigInteger.ONE;

        for (int i=2; i<=LIMIT; i++) {
            sum = sum.add(BigInteger.valueOf(i).pow(i));
        }

        return sum.divideAndRemainder(BigInteger.TEN.pow(NUM_DIGITS))[1];
    }
}

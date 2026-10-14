package com.example.fibonacci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class to generate Fibonacci sequence numbers.
 */
public class FibonacciGenerator {

    /**
     * Generates the first n terms of the Fibonacci sequence.
     * The sequence starts with 0 and 1: 0, 1, 1, 2, 3, 5, 8, 13, ...
     *
     * @param n the number of terms to generate (must be >= 0)
     * @return a List of Long containing the first n Fibonacci numbers
     * @throws IllegalArgumentException if n is negative
     */
    public List<Long> generate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The number of terms (n) cannot be negative: " + n);
        }

        if (n == 0) {
            return Collections.emptyList();
        }

        List<Long> series = new ArrayList<>(n);
        series.add(0L);

        if (n == 1) {
            return series;
        }

        series.add(1L);

        for (int i = 2; i < n; i++) {
            long next = series.get(i - 1) + series.get(i - 2);
            series.add(next);
        }

        return series;
    }

    /**
     * Computes the 0-indexed n-th Fibonacci number.
     * F(0) = 0, F(1) = 1, F(2) = 1, F(3) = 2, ...
     *
     * @param n 0-indexed position (must be >= 0)
     * @return the n-th Fibonacci number as a long
     * @throws IllegalArgumentException if n is negative
     */
    public long getNth(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index cannot be negative: " + n);
        }
        if (n == 0) return 0L;
        if (n == 1) return 1L;

        long prev2 = 0L;
        long prev1 = 1L;
        long current = 0L;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
}

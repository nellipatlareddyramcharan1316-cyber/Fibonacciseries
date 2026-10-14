package com.example.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Fibonacci Generator Tests")
class FibonacciGeneratorTest {

    private FibonacciGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new FibonacciGenerator();
    }

    @Test
    @DisplayName("Should return an empty list when generating 0 terms")
    void testGenerateZeroTerms() {
        List<Long> result = generator.generate(0);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return [0] when generating 1 term")
    void testGenerateOneTerm() {
        List<Long> result = generator.generate(1);
        assertEquals(List.of(0L), result);
    }

    @Test
    @DisplayName("Should return [0, 1] when generating 2 terms")
    void testGenerateTwoTerms() {
        List<Long> result = generator.generate(2);
        assertEquals(List.of(0L, 1L), result);
    }

    @Test
    @DisplayName("Should return correct series for first 5 terms")
    void testGenerateFiveTerms() {
        List<Long> result = generator.generate(5);
        assertEquals(List.of(0L, 1L, 1L, 2L, 3L), result);
    }

    @Test
    @DisplayName("Should return correct series for first 10 terms")
    void testGenerateTenTerms() {
        List<Long> result = generator.generate(10);
        List<Long> expected = List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when count is negative")
    void testGenerateNegativeCountThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(-1)
        );
        assertTrue(exception.getMessage().contains("cannot be negative"));
    }

    @ParameterizedTest(name = "F({0}) should be {1}")
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21",
            "9, 34",
            "10, 55"
    })
    @DisplayName("Should correctly calculate n-th Fibonacci value")
    void testGetNthFibonacci(int n, long expected) {
        assertEquals(expected, generator.getNth(n));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when index is negative for getNth")
    void testGetNthNegativeIndexThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> generator.getNth(-5)
        );
    }
}

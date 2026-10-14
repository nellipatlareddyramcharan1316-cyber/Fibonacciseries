package com.example.fibonacci;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class to run and demonstrate the Fibonacci generator.
 */
public class Main {

    public static void main(String[] args) {
        int count = 10; // Default count

        if (args.length > 0) {
            try {
                count = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for input: '" + args[0] + "'. Please enter an integer.");
                System.exit(1);
            }
        } else {
            System.out.print("Enter the number of Fibonacci terms to generate: ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
            } else if (scanner.hasNext()) {
                String input = scanner.next();
                System.err.println("Error: '" + input + "' is not a valid integer. Please enter an integer.");
                System.exit(1);
            } else {
                System.out.println("No input detected. Using default count: " + count);
            }
        }

        FibonacciGenerator generator = new FibonacciGenerator();

        System.out.println("==========================================");
        System.out.println("   Fibonacci Series Generator (Java)     ");
        System.out.println("==========================================");
        System.out.printf("Generating first %d Fibonacci numbers:%n", count);

        try {
            List<Long> fibonacciSeries = generator.generate(count);
            System.out.println(fibonacciSeries);

            System.out.println("\nTabular view:");
            for (int i = 0; i < fibonacciSeries.size(); i++) {
                System.out.printf("F(%d) = %d%n", i, fibonacciSeries.get(i));
            }
        } catch (IllegalArgumentException ex) {
            System.err.println("Execution Error: " + ex.getMessage());
            System.exit(1);
        }
        System.out.println("==========================================");
    }
}

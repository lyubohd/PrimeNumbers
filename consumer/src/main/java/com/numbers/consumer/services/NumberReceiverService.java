package com.numbers.consumer.services;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class NumberReceiverService {
    private static final String FILENAME = "prime_numbers.csv";

    public void findAndWritePrimeNumbers(List<Integer> numbers) {
        StringBuilder primeNumbers = findPrimeNumbers(numbers);
        try {
            writePrimeNumbersToCSV(primeNumbers);
        } catch (IOException e) {
            System.err.printf("%s cannot be created, or cannot be opened%n", FILENAME);
            throw new RuntimeException(e);
        }
    }

    private void writePrimeNumbersToCSV(StringBuilder primeNumbers) throws IOException {
        FileWriter writer = new FileWriter(FILENAME, true);

        writer.append(primeNumbers.toString()).append("\n");

        writer.flush();
        writer.close();
    }

    private static StringBuilder findPrimeNumbers(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for (Integer number : numbers) {
            boolean prime = true;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                sb.append(number).append(",");
            }
        }

        return sb.replace(Math.max(0, sb.length() - 1), sb.length(), "");
    }
}

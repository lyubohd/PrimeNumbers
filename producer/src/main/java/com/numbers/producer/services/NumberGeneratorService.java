package com.numbers.producer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
public class NumberGeneratorService {
    private static final String FILENAME = "random_numbers.csv";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${consumer.url}")
    private String serviceBUrl;

    private final List<Integer> numbersList = new ArrayList<>();
    private final RandomGenerator random = new Random();
    private static final int MAX_STREAM_SIZE = 101;
    private static final int MAX_NUMBER_COUNT = 6;

    @Scheduled(fixedRate = 1000)
    private void generateRandomNumbers() throws IOException {

        int count = random.nextInt(MAX_NUMBER_COUNT);
        List<Integer> currentSequenceOfNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(100);
            currentSequenceOfNumbers.add(randomNumber);
        }

        if (numbersList.size() + count < MAX_STREAM_SIZE) {
            numbersList.addAll(currentSequenceOfNumbers);
        } else {
            sendNumbersToConsumer();
            writeNumbersToCSV(numbersList);
            numbersList.clear();
        }
    }

    private void writeNumbersToCSV(List<Integer> integers) throws IOException {
        FileWriter writer = new FileWriter(FILENAME, true);
        StringBuilder sb = new StringBuilder();

        for (Integer integer : integers) {
            sb.append(integer.toString()).append(",");
            writer.append(integer.toString());
            writer.append(",");
        }

        sb.replace(sb.length() - 1, sb.length(), "");
        writer.append(sb.toString()).append("\n");

        writer.flush();
        writer.close();
    }

    private void sendNumbersToConsumer() {
        restTemplate.postForObject(serviceBUrl + "/numbers", numbersList, Void.class);
    }
}

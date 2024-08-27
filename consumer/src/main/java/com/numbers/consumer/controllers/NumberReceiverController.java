package com.numbers.consumer.controllers;

import com.numbers.consumer.services.NumberReceiverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/numbers")
public class NumberReceiverController {

    private final NumberReceiverService numberReceiverService;

    public NumberReceiverController(NumberReceiverService numberReceiverService) {
        this.numberReceiverService = numberReceiverService;
    }

    @PostMapping
    public void receiveNumbers(@RequestBody List<Integer> numbers) {
        numberReceiverService.findAndWritePrimeNumbers(numbers);
    }
}

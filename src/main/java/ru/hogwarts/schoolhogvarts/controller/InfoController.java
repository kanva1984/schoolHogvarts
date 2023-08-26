package ru.hogwarts.schoolhogvarts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Value("${server.port}")
    private int port;

    @GetMapping
    public int getPort() {
        return port;
    }

    @GetMapping("/sum-experiments")
    public void sum() {


        //#1
        LongStream stream1 = LongStream.iterate(1, a -> a + 1)
                .limit(1_000_000);
        long startTime = System.currentTimeMillis();
        long sum1 = stream1
                .reduce(0, (a, b) -> a + b);
        long endTime = System.currentTimeMillis();

        logger.info("Experiment 1 - stream with reduce. Sum = " +
                sum1 + ". Total calculation tim = " +
                (endTime - startTime));

        //#2
        LongStream stream2 = LongStream.iterate(1, a -> a + 1)
                .limit(1_000_000);
        startTime = System.currentTimeMillis();

        long sum2 = stream2
                .sum();
        endTime = System.currentTimeMillis();

        logger.info("Experiment 2 - stream with sum. Sum = " +
                sum2 + ". Total calculation tim = " +
                (endTime - startTime));


        //#3
        Stream<Integer> stream3 = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000);
        startTime = System.currentTimeMillis();

        int sum3 = stream3
                .parallel()
                .reduce(0, (a, b) -> a + b);
        endTime = System.currentTimeMillis();

        logger.info("Experiment 3 - parallel stream. Sum = " +
                sum3 + ". Total calculation tim = " +
                (endTime - startTime));


        //#4

        int sum4 = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i <= 1_000_000; i++) {
            sum4 += i;
        }
        endTime = System.currentTimeMillis();

        logger.info("Experiment 4 - fori. Sum = " +
                sum4 + ". Total calculation tim = " +
                (endTime - startTime));

    }
}

package com.stupin.carService.log;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class log {
    private final
    Logger log = LoggerFactory.getLogger(log.class);
    public int getRandom(int from, int to) {
        log.info("Generating random number from {} to {}", from, to);
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public void validateInputForRandom(int from, int to) {
        if (from < 0 || to < 0) {
            RuntimeException exception = new IllegalArgumentException("Wrong input [negative values]: from " + from + " to " + to);
            log.error("Wrong input", exception);
            throw exception;
        } else if (from >= to) {
            RuntimeException exception = new IllegalArgumentException("Wrong input [from>to]: from " + from + " to " + to);
            log.error("Wrong input", exception);
            throw exception;
        }
    }
}

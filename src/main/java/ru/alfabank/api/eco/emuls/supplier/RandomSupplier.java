package ru.alfabank.api.eco.emuls.supplier;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomSupplier {

    public int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current()
                                .nextInt(min, max);
    }

    public int getRandomNumber(int bound) {
        return ThreadLocalRandom.current()
                .nextInt(bound);
    }

    public String getRandomStringNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String getRandomAcus(int length) {
        return "X0" + RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }
}

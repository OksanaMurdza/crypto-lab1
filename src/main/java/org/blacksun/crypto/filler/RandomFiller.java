package org.blacksun.crypto.filler;

import java.util.Random;

public class RandomFiller implements UFiller<Double> {
    private double value;
    private int count;
    private final Random random;

    public RandomFiller(int amount) {
        this.value = 1.0;
        this.count = amount;
        random = new Random();
    }

    @Override
    public Double generate(int index) {
        double newValue;
        do {
            newValue = random.nextDouble();
        } while (newValue >= value);
        if (--count == 0)
            return value;
        value -= newValue;
        return newValue;
    }
}

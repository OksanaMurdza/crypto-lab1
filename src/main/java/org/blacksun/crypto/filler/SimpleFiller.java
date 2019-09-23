package org.blacksun.crypto.filler;

public class SimpleFiller implements UFiller<Double> {
    private final double value;

    public SimpleFiller(int amount) {
        this.value = 1.0 / amount;
    }

    @Override
    public Double generate(int index) {
        return value;
    }
}

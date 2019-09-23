package org.blacksun.crypto.filler;

public class SimpleBiFiller implements BiFiller<Double> {
    private final double value;

    public SimpleBiFiller(int amount) {
        value = 1.0 / amount;
    }

    @Override
    public Double generate(int row, int col) {
        return value;
    }
}

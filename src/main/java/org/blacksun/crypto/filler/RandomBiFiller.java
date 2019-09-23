package org.blacksun.crypto.filler;

public class RandomBiFiller implements BiFiller<Double> {
    private final RandomFiller[] fillers;

    public RandomBiFiller(int row, int col) {
        fillers = new RandomFiller[row];
        for (int i = 0; i < fillers.length; i++) {
            fillers[i] = new RandomFiller(col);
        }
    }

    @Override
    public Double generate(int row, int col) {
        return fillers[row].generate(col);
    }
}

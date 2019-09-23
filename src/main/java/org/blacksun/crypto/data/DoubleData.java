package org.blacksun.crypto.data;

public class DoubleData extends AbstractProvider<Double> {
    private final double[][] data;

    public DoubleData(int rows, int columns) {
        super(rows, columns);
        data = new double[rows][columns];
    }

    @Override
    public Double get(int row, int column) {
        return data[row][column];
    }

    @Override
    public void set(int row, int column, Double value) {
        data[row][column] = value;
    }

    @Override
    public void set(int row, int column, String value) {
        set(row, column, Double.parseDouble(value));
    }
}

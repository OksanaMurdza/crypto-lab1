package org.blacksun.crypto.data;

public class IntData extends AbstractProvider<Integer> {
    private final int[][] data;

    public IntData(int rows, int columns) {
        super(rows, columns);
        data = new int[rows][columns];
    }

    @Override
    public Integer get(int row, int column) {
        return data[row][column];
    }

    @Override
    public void set(int row, int column, Integer value) {
        data[row][column] = value;
    }

    @Override
    public void set(int row, int column, String value) {
        set(row, column, Integer.parseInt(value));
    }
}

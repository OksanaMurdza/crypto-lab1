package org.blacksun.crypto.data;

import org.blacksun.crypto.filler.BiFiller;
import org.blacksun.crypto.filler.UFiller;

import java.io.*;

public abstract class AbstractProvider<T> implements DataProvider<T> {
    private final int rows;
    private final int columns;

    public AbstractProvider(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void fill(BiFiller<T> filler) {
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                set(row, column, filler.generate(row, column));
            }
        }
    }

    @Override
    public void fillColumn(int column, UFiller<T> filler) {
        for (int row = 0; row < rows; ++row) {
            set(row, column, filler.generate(row));
        }
    }

    @Override
    public void fillRow(int row, UFiller<T> filler) {
        for (int column = 0; column < columns; ++column) {
            set(row, column, filler.generate(column));
        }
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public void save(String filename) throws IOException {
        File file = new File(filename);
        try (PrintStream stream = new PrintStream(new FileOutputStream(file))) {
            for (int row = 0; row < getRows(); ++row) {
                for (int column = 0; column < getColumns(); ++column) {
                    stream.println(get(row, column));
                }
            }
        }
    }

    @Override
    public void load(String filename) throws IOException {
        File file = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int row = 0; row < getRows(); ++row) {
                for (int column = 0; column < getColumns(); ++column) {
                    set(row, column, reader.readLine());
                }
            }
        }
    }

    @Override
    public void validateRow(int row, Validator<T> validator) {
        for (int column = 0; column < getColumns(); ++column) {
            T value = get(row, column);
            if (!validator.isValid(value)) {
                throw new IllegalStateException(String.format("Value %s at %d:%d is invalid", value, row, column));
            }
        }
    }

    @Override
    public void validateColumn(int column, Validator<T> validator) {
        for (int row = 0; row < getRows(); ++row) {
            T value = get(row, column);
            if (!validator.isValid(value)) {
                throw new IllegalStateException(String.format("Value %s at %d:%d is invalid", value, row, column));
            }
        }
    }
}

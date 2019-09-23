package org.blacksun.crypto.data;

import org.blacksun.crypto.filler.BiFiller;
import org.blacksun.crypto.filler.UFiller;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataProvider<T> {
    int getColumns();
    int getRows();
    T get(int row, int column);
    void set(int row, int column, T value);
    void set(int row, int column, String value);
    void fill(BiFiller<T> filler);
    void fillRow(int row, UFiller<T> filler);
    void fillColumn(int column, UFiller<T> filler);
    void validateRow(int row, Validator<T> validator);
    void validateColumn(int column, Validator<T> validator);
    void save(String file) throws IOException;
    void load(String file) throws IOException;
}

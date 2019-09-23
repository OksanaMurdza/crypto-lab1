package org.blacksun.crypto.filler;

@FunctionalInterface
public interface BiFiller<T> {
    T generate(int row, int col);
}

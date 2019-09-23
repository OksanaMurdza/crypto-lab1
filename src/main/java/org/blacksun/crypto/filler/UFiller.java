package org.blacksun.crypto.filler;

@FunctionalInterface
public interface UFiller<T> {
    T generate(int index);
}

package org.blacksun.crypto.formatter;

@FunctionalInterface
public interface DataFormatter<T> {
    String format(T value);
}

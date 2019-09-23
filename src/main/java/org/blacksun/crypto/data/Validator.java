package org.blacksun.crypto.data;

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T value);
}

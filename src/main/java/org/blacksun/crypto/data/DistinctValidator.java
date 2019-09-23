package org.blacksun.crypto.data;

import java.util.HashSet;

public class DistinctValidator<T> implements Validator<T> {
    private HashSet<T> processed = new HashSet<>();

    @Override
    public boolean isValid(T value) {
        if (processed.contains(value))
            return false;
        processed.add(value);
        return true;
    }
}

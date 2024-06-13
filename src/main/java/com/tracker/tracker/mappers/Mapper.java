package com.tracker.tracker.mappers;

public interface Mapper<S, T> {
    T mapTo(S source);

    S mapFrom(T destination);
}

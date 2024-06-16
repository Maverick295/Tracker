package com.tracker.tracker.mappers;

import com.tracker.tracker.dto.item.ItemDTO;
import com.tracker.tracker.entities.Item;

public interface Mapper<S, T> {
    T mapTo(S source);

    S mapFrom(T destination);
}

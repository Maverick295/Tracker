package com.tracker.tracker.services.item;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Item;
import com.tracker.tracker.entities.User;

import java.util.List;

public interface ItemService {

    List<Item> getAllBySupplier(User user);

    List<Item> getAllByCompany(Company company);

    Item getByUuid(String uuid);

    void deleteByUuid(String uuid);

    void save(Item item);

    Item enrichItem(Item item, String companyName);
}

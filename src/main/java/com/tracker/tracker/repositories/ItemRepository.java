package com.tracker.tracker.repositories;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Item;
import com.tracker.tracker.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Optional<Item> findByUuid(String uuid);

    List<Item> findAllBySupplier(User supplier);

    List<Item> findByCompany(Company company);

    void deleteByUuid(String uuid);
}

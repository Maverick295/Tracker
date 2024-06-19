package com.tracker.tracker.services.item;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Item;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.repositories.ItemRepository;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.user.UserService;
import com.tracker.tracker.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public ItemServiceImpl(
            ItemRepository itemRepository,
            UserService userService,
            CompanyService companyService)
    {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public List<Item> findAllBySupplier(User user) {
        return itemRepository.findAllBySupplier(user);
    }

    @Override
    public List<Item> findAllByCompany(Company company) {
        return itemRepository.findByCompany(company);
    }

    @Override
    public Item findByUuid(String uuid) {
        return itemRepository.findByUuid(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }

    @Override
    @Transactional
    public void deleteByUuid(String uuid) {
        itemRepository.deleteByUuid(uuid);
    }

    @Override
    public void save(@Validated(Item.Create.class) Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item enrichItem(@Validated(Item.Update.class) Item item, String companyName) {
        User supplier = userService.getAuthenticatedUser();
        Company company = companyService.findByName(companyName);

        return item
            .setSupplier(supplier)
            .setCompany(company)
            .setUuid(ServiceUtil.generateUuid());
    }
}

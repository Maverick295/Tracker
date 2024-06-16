package com.tracker.tracker.controllers.item;

import com.tracker.tracker.dto.item.ItemDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Item;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.item.ItemService;
import com.tracker.tracker.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService, UserService userService, CompanyService companyService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.userService = userService;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    // TODO: добавить проверки во все методы
    @GetMapping("/{username}/{companyName}")
    public List<ItemDTO> getAllCompanyItems(@PathVariable String companyName, @PathVariable String username) {
        Company company = companyService.findByName(companyName);
        List<Item> items = itemService.findAllByCompany(company);

        return items.stream()
            .map(item -> modelMapper.map(item, ItemDTO.class))
            .collect(Collectors.toList());
    }

    @GetMapping
    public List<ItemDTO> getAllSupplierItems() {
        User user = userService.getAuthenticatedUser();
        List<Item> items = itemService.findAllBySupplier(user);

        return items.stream()
            .map(item -> modelMapper.map(item, ItemDTO.class))
            .collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ItemDTO moreAboutItem(@PathVariable String uuid) {
        Item item = itemService.findByUuid(uuid);

        return modelMapper.map(item, ItemDTO.class);
    }

    @PatchMapping("/{uuid}/edit")
    public ResponseEntity<ItemDTO> editItem(@PathVariable String uuid, @RequestBody ItemDTO itemDTO) {
        Item item = itemService.findByUuid(uuid);
        modelMapper.map(itemDTO, item);
        itemService.save(item);

        return ResponseEntity.ok(modelMapper.map(item, ItemDTO.class));
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createItem(@RequestBody ItemDTO dto) {
        Item item = modelMapper.map(dto, Item.class);
        itemService.save(itemService.enrichItem(item, dto.getCompany()));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable String uuid) {
        itemService.deleteByUuid(uuid);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

package com.tracker.tracker.controllers.item;

import com.tracker.tracker.dto.item.ItemDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Item;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.errors.AccessDeniedException;
import com.tracker.tracker.errors.EntityNotUpdatedException;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.item.ItemService;
import com.tracker.tracker.services.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
    public ItemController(
            ItemService itemService,
            UserService userService,
            CompanyService companyService,
            ModelMapper modelMapper)
    {
        this.itemService = itemService;
        this.userService = userService;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    // TODO: добавить проверки во все методы
    @GetMapping("/{username}/{companyName}")
    public List<ItemDTO> getAllCompanyItems(
            @PathVariable String companyName,
            @PathVariable String username)
    {
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
        // TODO: подумать может разрешить другим смотреть чужой товар
        if (!item.getSupplier().equals(userService.getAuthenticatedUser())) {
            throw new AccessDeniedException("You do not have permission to see this item");
        }

        return modelMapper.map(item, ItemDTO.class);
    }

    @PatchMapping("/{uuid}/edit")
    public ResponseEntity<ItemDTO> editItem(
            @PathVariable String uuid,
            @RequestBody @Validated({ItemDTO.Update.class, Default.class}) ItemDTO itemDTO,
            BindingResult bindingResult)
    {

        checkErrors(bindingResult);

        Item item = itemService.findByUuid(uuid);

        if (!item.getSupplier().equals(userService.getAuthenticatedUser())) {
            throw new AccessDeniedException("You do not have permission to edit this item");
        }

        modelMapper.map(itemDTO, item);
        itemService.save(item);

        return ResponseEntity.ok(modelMapper.map(item, ItemDTO.class));
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createItem(
            @RequestBody @Validated( {Default.class}) ItemDTO dto,
            BindingResult bindingResult)
    {
        checkErrors(bindingResult);

        Item item = modelMapper.map(dto, Item.class);
        item = itemService.enrichItem(item, dto.getCompany());
        itemService.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private void checkErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errors.append(fieldError.getField())
                        .append(" : ").append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new EntityNotUpdatedException(errors.toString());
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable String uuid) {
        Item item = itemService.findByUuid(uuid);

        if (!item.getSupplier().equals(userService.getAuthenticatedUser())) {
            throw new AccessDeniedException("You do not have permission to delete this item");
        }

        itemService.deleteByUuid(uuid);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

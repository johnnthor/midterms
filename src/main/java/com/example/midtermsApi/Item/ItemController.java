package com.example.midtermsApi.Item;


import com.example.midtermsApi.Customer.CustomerRepository;
import com.example.midtermsApi.Resources.Condition;
import com.example.midtermsApi.Resources.RuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// ====================================================================================================================================================================================== \\

@RestController
public class ItemController {


    @Autowired
    private ItemRepository itemRepository;


    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customers/all/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }


    @PostMapping("/customers/{customerId}/items")
    public Item createItem(@PathVariable (value = "customerId") Long customerId,
                           @Valid @RequestBody Item item) {
        return customerRepository.findById(customerId).map(customer -> {
            item.setCustomer(customer);
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Customer id " + customerId + " not found"));
    }

    @PutMapping("/customers/{customerId}/items/{itemId}")
    public Item updateItem(@PathVariable (value = "customerId") Long customerId,
                           @PathVariable (value = "itemId") Long itemId,
                           @Valid @RequestBody Item itemRequest) {
        if(!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Item id " + customerId + " not found");
        }

        return itemRepository.findById(itemId).map(item -> {
            item.setProduct_name(itemRequest.getProduct_name());
            item.setManufacturer_name(itemRequest.getManufacturer_name());
            item.setPrice(itemRequest.getPrice());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Customer id " + itemId + "not found"));
    }


    @DeleteMapping(value = "/customers/{customerId}/items/{itemId}")
    public Condition deleteItem(@PathVariable("itemId") Long itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists) {
            throw new IllegalStateException("item with id " + itemId + " does not exists");
        }
        itemRepository.deleteById(itemId);
        return Condition.DELETE_IS_SUCCESSFUL;
    }

    @DeleteMapping("/customers/all/items/all")
    public Condition deleteItems() {
        customerRepository.deleteAll();
        return Condition.ALL_ITEMS_DELETED;
    }
}


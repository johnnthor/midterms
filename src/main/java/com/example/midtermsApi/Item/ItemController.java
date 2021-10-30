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


    @PostMapping("/customers/{customerid}/items")
    public Item createItem(@PathVariable (value = "customerid") Long customerid,
                           @Valid @RequestBody Item item) {
        return customerRepository.findById(customerid).map(customer -> {
            item.setCustomer(customer);
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Customer id " + customerid + " not found"));
    }

    @PutMapping("/customers/{customerid}/items/{itemid}")
    public Item updateItem(@PathVariable (value = "customerid") Long customerid,
                           @PathVariable (value = "itemid") Long itemid,
                           @Valid @RequestBody Item itemRequest) {
        if(!customerRepository.existsById(customerid)) {
            throw new RuntimeException("Item id " + customerid + " not found");
        }

        return itemRepository.findById(itemid).map(item -> {
            item.setProduct_name(itemRequest.getProduct_name());
            item.setManufacturer_name(itemRequest.getManufacturer_name());
            item.setPrice(itemRequest.getPrice());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Customer id " + itemid + "not found"));
    }


    @DeleteMapping(value = "/customers/{customerid}/items/{itemid}")
    public Condition deleteItem(@PathVariable("itemid") Long itemid) {
        boolean exists = itemRepository.existsById(itemid);
        if (!exists) {
            throw new IllegalStateException("item with id " + itemid + " does not exists");
        }
        itemRepository.deleteById(itemid);
        return Condition.DELETE_IS_SUCCESSFUL;
    }

    @DeleteMapping("/customers/all/items/all")
    public Condition deleteItems() {
        customerRepository.deleteAll();
        return Condition.ALL_ITEMS_DELETED;
    }
}


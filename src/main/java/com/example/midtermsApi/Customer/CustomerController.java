package com.example.midtermsApi.Customer;


import com.example.midtermsApi.Resources.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// ====================================================================================================================================================================================== \\

@RestController
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    @GetMapping(value = "/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Long id) {
        return customerRepository.findById(id);
    }


    @PostMapping("/customers/register")
    public Condition registerCustomer(@Valid @RequestBody Customer newCustomer) {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("New customer: " + newCustomer.toString());

        for (Customer customer : customers) {
            System.out.println("Registered customer: " + newCustomer);

            if (customer.equals(newCustomer)) {
                System.out.println("Customer Already exists!");
                return Condition.USER_ALREADY_EXISTS;
            }
        }
        customerRepository.save(newCustomer);
        return Condition.USER_REGISTERED;
    }


    @PostMapping("/customers/login")
    public Condition loginCustomer(@Valid @RequestBody Customer customer) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer other : customers) {
            if (other.equals(customer)) {

                return Condition.USER_LOGIN;
            }
        }        return Condition.REQUEST_FAILED;
    }


    @PostMapping("/customers/logout")
    public Condition logOutCustomer(@Valid @RequestBody Customer customer) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer other : customers) {
            if (other.equals(customer)) {

                return Condition.USER_LOGOUT;
            }
        }        return Condition.REQUEST_FAILED;
    }


    @PutMapping("/customers/{customerid}")
    public Customer updatePost(@PathVariable Long customerid, @Valid @RequestBody Customer customerRequest) {
        return customerRepository.findById(customerid).map(customer -> {
            customer.setEmail(customerRequest.getEmail());
            customer.setEmail(customerRequest.getEmail());
            customer.setName(customerRequest.getName());
            customer.setPassword(customerRequest.getPassword());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer id " + customerid + " not found"));
    }


    @DeleteMapping("/customers/{customerid}")
    public Condition deleteCustomer(@PathVariable("customerid") Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id " + id + " does not exists");
        }
        customerRepository.deleteById(id);
        return Condition.DELETE_IS_SUCCESSFUL;
    }


    @DeleteMapping("/customers/deleteall")
    public Condition deleteCustomers() {
        customerRepository.deleteAll();
        return Condition.ALL_USERS_DELETED;
    }
}
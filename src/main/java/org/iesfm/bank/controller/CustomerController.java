package org.iesfm.bank.controller;

import org.iesfm.bank.Customer;
import org.iesfm.bank.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers")
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customers")
    public void createCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/customers/{id}")
    public void deleteCustomer(@PathVariable("id") int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "The customer not exists in the Bank"
        );
    }
}

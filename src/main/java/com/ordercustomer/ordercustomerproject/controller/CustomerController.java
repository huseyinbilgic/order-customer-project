package com.ordercustomer.ordercustomerproject.controller;

import com.ordercustomer.ordercustomerproject.entities.Customer;
import com.ordercustomer.ordercustomerproject.requests.CustomerRequest;
import com.ordercustomer.ordercustomerproject.responses.CustomerResponse;
import com.ordercustomer.ordercustomerproject.responses.SearchCustomerNameContainingResponse;
import com.ordercustomer.ordercustomerproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
    @GetMapping("/search/by-name/{name}")
    public ResponseEntity<List<SearchCustomerNameContainingResponse>> search(@PathVariable String name){
        return ResponseEntity.ok(customerService.searchByContainsName(name));
    }
    @GetMapping("/customers-not-orders")
    public ResponseEntity<List<CustomerResponse>> findAllOrdersIsEmpty(){
        return ResponseEntity.ok(customerService.findAllOrdersIsEmpty());
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity(customerService.create(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable Long customerId,
            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.update(customerId, customerRequest));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId) {
        customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}

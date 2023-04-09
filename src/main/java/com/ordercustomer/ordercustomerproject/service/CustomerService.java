package com.ordercustomer.ordercustomerproject.service;

import com.ordercustomer.ordercustomerproject.requests.CustomerRequest;
import com.ordercustomer.ordercustomerproject.responses.CustomerResponse;
import com.ordercustomer.ordercustomerproject.responses.SearchCustomerNameContainingResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(Long id);
    CustomerResponse create(CustomerRequest customerRequest);
    CustomerResponse update(Long id, CustomerRequest customerRequest);
    void deleteById(Long id);
    List<SearchCustomerNameContainingResponse> searchByContainsName(String name);

    List<CustomerResponse> findAllOrdersIsEmpty();

}

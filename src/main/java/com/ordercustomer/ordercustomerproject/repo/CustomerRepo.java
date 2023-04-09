package com.ordercustomer.ordercustomerproject.repo;

import com.ordercustomer.ordercustomerproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContaining(String name);

    List<Customer> findAllByOrdersIsEmpty();
}

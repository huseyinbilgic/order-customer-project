package com.ordercustomer.ordercustomerproject.service;

import com.ordercustomer.ordercustomerproject.requests.OrderAfterCreationDateRequest;
import com.ordercustomer.ordercustomerproject.requests.OrderRequest;
import com.ordercustomer.ordercustomerproject.responses.OrderResponse;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();
    OrderResponse findById(Long id);
    OrderResponse create(OrderRequest orderRequest);
    OrderResponse update(Long id,OrderRequest orderRequest);
    void deleteById(Long id);

    List<OrderResponse> findAllAfterCreationDate(OrderAfterCreationDateRequest dateRequest);
}

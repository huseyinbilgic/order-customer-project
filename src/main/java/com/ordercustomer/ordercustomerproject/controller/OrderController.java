package com.ordercustomer.ordercustomerproject.controller;

import com.ordercustomer.ordercustomerproject.entities.Order;
import com.ordercustomer.ordercustomerproject.requests.OrderAfterCreationDateRequest;
import com.ordercustomer.ordercustomerproject.requests.OrderRequest;
import com.ordercustomer.ordercustomerproject.responses.OrderResponse;
import com.ordercustomer.ordercustomerproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    @GetMapping("/search/after-date")
    public ResponseEntity<List<OrderResponse>> findAllAfterCreationDate(
            @RequestBody OrderAfterCreationDateRequest dateRequest) {
        return ResponseEntity.ok(orderService.findAllAfterCreationDate(dateRequest));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity(orderService.create(orderRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> update(
            @PathVariable Long orderId,
            @RequestBody OrderRequest orderRequest
    ) {
        return ResponseEntity.ok(orderService.update(orderId, orderRequest));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }
}

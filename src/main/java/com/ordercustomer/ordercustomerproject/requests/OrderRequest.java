package com.ordercustomer.ordercustomerproject.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {
    private BigDecimal totalPrice;
    private Long customerId;
}

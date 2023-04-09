package com.ordercustomer.ordercustomerproject.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderResponse {
    private Long id;
    private BigDecimal totalPrice;
    private CustomerResponse customerResponse;
    private Timestamp createdDate;
}

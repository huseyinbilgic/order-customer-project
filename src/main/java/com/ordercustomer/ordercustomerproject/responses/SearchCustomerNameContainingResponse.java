package com.ordercustomer.ordercustomerproject.responses;

import lombok.Data;

import java.util.List;

@Data
public class SearchCustomerNameContainingResponse {
    private Long id;
    private String name;
    private Integer age;
    private List<Long> orderIds;
}

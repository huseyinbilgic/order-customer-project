package com.ordercustomer.ordercustomerproject.requests;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderAfterCreationDateRequest {
    private Date date;
}

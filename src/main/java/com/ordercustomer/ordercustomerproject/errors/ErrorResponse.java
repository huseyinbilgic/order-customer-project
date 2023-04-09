package com.ordercustomer.ordercustomerproject.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ErrorResponse {
    private HttpStatus httpStatus;

    private String message;

    private long timeStamp;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus httpStatus, String message, long timeStamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}

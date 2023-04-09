package com.ordercustomer.ordercustomerproject.errors;

public class OrderException extends RuntimeException{
    public OrderException(){

    }
    public OrderException(String message){
        super(message);
    }
}

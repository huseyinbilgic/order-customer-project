package com.ordercustomer.ordercustomerproject.errors;

public class CustomerException extends RuntimeException{
    public CustomerException(){

    }
    public CustomerException(String message){
        super(message);
    }

}

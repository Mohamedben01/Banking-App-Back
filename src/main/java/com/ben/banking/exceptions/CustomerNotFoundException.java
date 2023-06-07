package com.ben.banking.exceptions;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String customerNotFound) {
        super(customerNotFound);
    }
}

package com.ben.banking.exceptions;

public class BankAccountNotFoundException extends Throwable {
    public BankAccountNotFoundException(String bankAccountNotFound) {
        super(bankAccountNotFound);
    }
}

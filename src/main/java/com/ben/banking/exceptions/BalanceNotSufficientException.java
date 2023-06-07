package com.ben.banking.exceptions;

public class BalanceNotSufficientException extends Throwable {
    public BalanceNotSufficientException(String balanceNotFound) {
        super(balanceNotFound);
    }
}

package com.ben.banking.services;

import com.ben.banking.dtos.AccountHistoryDTO;
import com.ben.banking.dtos.AccountOperationDTO;
import com.ben.banking.dtos.BankAccountDTO;
import com.ben.banking.dtos.CurrentBankAccountDTO;
import com.ben.banking.dtos.CustomerDTO;
import com.ben.banking.dtos.SavingBankAccountDTO;
import com.ben.banking.exceptions.BalanceNotSufficientException;
import com.ben.banking.exceptions.BankAccountNotFoundException;
import com.ben.banking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccountDTO> bankAccountList();
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
    List<AccountOperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws  BankAccountNotFoundException;
}

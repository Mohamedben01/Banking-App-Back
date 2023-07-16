package com.ben.banking.web;

import com.ben.banking.dtos.AccountHistoryDTO;
import com.ben.banking.dtos.AccountOperationDTO;
import com.ben.banking.dtos.BankAccountDTO;
import com.ben.banking.exceptions.BankAccountNotFoundException;
import com.ben.banking.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/{accountId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public BankAccountDTO getBankAccount(@PathVariable(name = "accountId") String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/{accountId}/operations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/{accountId}/pageOperations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }
}

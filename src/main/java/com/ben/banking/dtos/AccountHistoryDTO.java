package com.ben.banking.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private String accountId;
    private double balance;
    private int totalPages;
    private int pageSize;
    private int currentPage;
    private List<AccountOperationDTO> accountOperationDTOS;
}

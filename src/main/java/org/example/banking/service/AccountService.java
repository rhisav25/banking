package org.example.banking.service;

import org.example.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long Id,double amount);
    AccountDto withdraw(Long Id,double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long Id);
}

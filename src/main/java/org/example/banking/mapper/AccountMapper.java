package org.example.banking.mapper;

import org.example.banking.dto.AccountDto;
import org.example.banking.entity.Account;

//creating one methods to map account to dto and vice versa
public class AccountMapper {
    public static Account maptoAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
                );
        return account;
    }

    public static AccountDto maptoAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}

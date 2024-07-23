package org.example.banking.service.Impl;

import org.example.banking.dto.AccountDto;
import org.example.banking.entity.Account;
import org.example.banking.mapper.AccountMapper;
import org.example.banking.repository.AccountRepository;
import org.example.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //understand what is happening here
    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
   }

    @Override
    public AccountDto getAccountById(Long Id) {

        Account account = accountRepository.
                findById(Id).
                orElseThrow(()-> new RuntimeException("Account not found"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long Id, double amount) {
        //this check if account with a particular id exists or not
        Account account = accountRepository.
                findById(Id).
                orElseThrow(()-> new RuntimeException("Account not found"));

        double total = amount + account.getBalance();
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account = accountRepository.
                findById(Id).
                orElseThrow(()-> new RuntimeException("Account not found"));

        if(amount > account.getBalance()){
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account>accounts =  accountRepository.findAll();
        return accounts.stream().map((account)-> AccountMapper.maptoAccountDto(account)).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long Id) {
        Account account = accountRepository.
                findById(Id).
                orElseThrow(()-> new RuntimeException("Account not found"));

        accountRepository.deleteById(Id);
    }
}

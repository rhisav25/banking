package org.example.banking.controller;

import org.example.banking.dto.AccountDto;
import org.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
  private AccountService accountService;
  public AccountController(AccountService accountService) {
      this.accountService = accountService;
  }

  //Add account Restapi
  @PostMapping
  public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto) {
      return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
  }

  //Get account rest api
    @GetMapping ("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
      AccountDto accountDto = accountService.getAccountById(id);
      return ResponseEntity.ok(accountDto);
    }

    //creating deposit method as key value pair
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto>deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
      Double amount = request.get("amount");
      AccountDto accountDto = accountService.deposit(id,amount);
      return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto>withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request){
      Double amount = request.get("amount");
      AccountDto accountDto = accountService.withdraw(id,amount);
      return ResponseEntity.ok(accountDto);
    }

    //Get all account restapi
   @GetMapping
   public ResponseEntity<List<AccountDto>>GetAllAccounts(){
      List<AccountDto>accounts = accountService.getAllAccounts();
      return ResponseEntity.ok(accounts);
   }

   //delete account rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount(@PathVariable Long id){
      accountService.deleteAccount(id);
      return ResponseEntity.ok("Deleted account successfully");
    }
}

package org.example.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;

    public Account(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
}

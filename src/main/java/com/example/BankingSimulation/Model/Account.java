package com.example.BankingSimulation.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@Entity
public class Account {


    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float balance;
    private int pin;

    @Embedded
    private Transaction transaction;
    public Account(String name,  float balance, int pin) {
        this.name = name;
        this.balance = balance;
        this.transaction = new Transaction(balance);
        this.pin = pin;
    }

    public boolean deposit(int pin, int amount) {
        if (this.pin == pin) {
            this.balance += amount;
            this.transaction.update("Deposit: " +amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(int pin, int amount) {
        if (this.pin == pin && amount < this.balance) {
            this.balance = this.balance - amount;
            this.transaction.update("Withdraw: " + amount);
            return true;
        }
        return false;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public Account() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

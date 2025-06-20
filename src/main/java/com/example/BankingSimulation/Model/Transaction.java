package com.example.BankingSimulation.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@Embeddable
public class Transaction {



    @ElementCollection
    @CollectionTable(
            name = "account_transaction_history",
            joinColumns = @JoinColumn(name = "account_id")
    )
    List<String> transaction = new ArrayList<>();
    public Transaction(float balance) {
        transaction.add("Deposit: "+Integer.toString((int)balance));
    }

    public void setTransaction(List<String> transaction) {
        this.transaction = transaction;
    }

    public List<String> display(){
        return transaction;
    }



    public void update(String s){
        transaction.add(s);
    }

    public Transaction() {
    }
}

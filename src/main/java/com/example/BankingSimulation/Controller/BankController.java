package com.example.BankingSimulation.Controller;


import com.example.BankingSimulation.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class  BankController {

    @Autowired
    private BankService service;


    @PostMapping("/new")
    public ResponseEntity<String> newCustomers(@RequestParam String name,
                                              @RequestParam String password,
                                              @RequestParam float balance,
                                              @RequestParam int pin){
        return service.newCustomers(name,password,balance,pin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String name,
                        @RequestParam String password,
                        @RequestParam int id){
        return service.login(name,password,id);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(
                          @RequestParam int amount,
                          @RequestParam int pin){
        return service.deposit(amount,pin);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withDraw(
                           @RequestParam int amount,
                           @RequestParam int pin){
        return service.withDraw(amount,pin);
    }

    @GetMapping("/balance")
    public ResponseEntity<String> balance(){
        return service.checkBalance();
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<String>> transaction(){
        return service.transactionHistory();
    }

}

package com.example.BankingSimulation.Controller;


import com.example.BankingSimulation.Model.Customer;
import com.example.BankingSimulation.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

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

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@PathVariable int id,
                          @RequestParam int amount,
                          @RequestParam int pin){
        return service.deposit(id,amount,pin);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withDraw(@PathVariable int id,
                           @RequestParam int amount,
                           @RequestParam int pin){
        return service.withDraw(id,amount,pin);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<String> balance(@PathVariable int id){
        return service.checkBalance(id);
    }

}

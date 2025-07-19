package com.example.BankingSimulation.Controller;


import com.example.BankingSimulation.Model.Customer;
import com.example.BankingSimulation.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class  BankController {

    @Autowired
    private BankService service;

    @GetMapping()
    public String display(){
        return "Hello World";
    }

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

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ResponseEntity.ok("Logged in as: " + username);
    }

}

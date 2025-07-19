package com.example.BankingSimulation.Service;

import com.example.BankingSimulation.Config.JwtFilter;
import com.example.BankingSimulation.Model.Account;
import com.example.BankingSimulation.Model.Customer;
import com.example.BankingSimulation.Repo.customerRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;

@Service
@Getter
@Setter
public class BankService {

    @Autowired
    private customerRepo repo;
    Integer custId=0;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ResponseEntity<String> newCustomers(String name, String password, float balance, int pin){
        if((balance >= 1000) && (password.length() > 4) && (pin > 999)){
            Customer customer = new Customer(name, encoder.encode(password), balance,pin);
            repo.save(customer);
            return new ResponseEntity<>("Customers Created With ID: "+customer.getId(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Balance Should be more than 1000 and ID Should be 4 digit pin",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> login(String name, String password, int id) {
        Customer customer = repo.findByUsername(name);
        if(customer!=null && encoder.matches(password,customer.getPassword()) && customer.getId()==id){
            String token = jwtService.generateToken(name);
            return  new ResponseEntity<>("JWTToken:"+token,HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deposit(int amount,int pin) {
        // Get the currently authenticated username from the JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // From JWT token

        Customer c = repo.findByUsername(username);
        if (c == null) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        if (amount > 0 && c.getAccount().deposit(pin, amount)) {
            repo.save(c);
            return new ResponseEntity<>("Deposited Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Deposit Failed", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> withDraw( int amount, int pin) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Customer c= repo.findByUsername(authentication.getName());
        if(c.getAccount().withdraw(pin,amount)){
            repo.save(c);
            return new ResponseEntity<>("Withdraw Successful",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to Withdraw",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> checkBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer c=repo.findByUsername(authentication.getName());
        return new ResponseEntity<>("Balance: "+c.getAccount().getBalance(),HttpStatus.OK);
    }

    public ResponseEntity<List<String>> transactionHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer c=repo.findByUsername(authentication.getName());
        return new ResponseEntity<>(c.getAccount().getTransaction().display(),HttpStatus.FOUND);
    }
}

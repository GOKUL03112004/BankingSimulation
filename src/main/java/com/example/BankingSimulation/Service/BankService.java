package com.example.BankingSimulation.Service;

import com.example.BankingSimulation.Model.Account;
import com.example.BankingSimulation.Model.Customer;
import com.example.BankingSimulation.Repo.customerRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

@Service
@Getter
@Setter
public class BankService {

    @Autowired
    private customerRepo repo;
    Integer custId=0;



    public ResponseEntity<String> newCustomers(String name, String password, float balance, int pin){
        if((balance >= 1000) && (password.length() > 4) && (pin > 999)){
            Customer customer = new Customer(name,password,balance,pin);
            repo.save(customer);
            return new ResponseEntity<>("Customers Created With ID: "+customer.getId(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Balance Should be more than 1000 and ID Should be 4 digit pin",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> login(String name, String password, int id) {
        Customer customer=repo.getReferenceById(id);
        if(customer.getPassword().equals(password) && customer.getName().equals(name)){
            customer.setLogin(true);
            repo.save(customer);
            return new ResponseEntity<>("Login Succcesfull",HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Failed",HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<String> deposit(int id,int amount,int pin){
        Customer c=repo.getReferenceById(id);
        if(amount>0 && c.getAccount().deposit(pin,amount) && c.isLogin()){
            repo.save(c);
            return new ResponseEntity<>("Deposited Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Deposit Failed",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> withDraw(int id, int amount, int pin) {
        Customer c=repo.getReferenceById(id);
        if(c.getAccount().withdraw(pin,amount) && c.isLogin()){
            repo.save(c);
            return new ResponseEntity<>("Withdraw Successful",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to Withdraw",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> checkBalance(int id) {
        Customer c=repo.getReferenceById(id);
        return new ResponseEntity<>("Balance: "+c.getAccount().getBalance(),HttpStatus.OK);
    }
}

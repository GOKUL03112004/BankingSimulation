package com.example.BankingSimulation.Repo;

import com.example.BankingSimulation.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo extends JpaRepository<Customer,Integer> {

    Customer findByUsername(String username);
}

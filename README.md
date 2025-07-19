# 🏦 Banking Simulator

A secure, RESTful banking system built using **Spring Boot**, **Spring Security**, **JWT**, and **MySQL**. This project simulates core banking functionalities like user registration, authentication, balance management, and transaction tracking.

## 🚀 Features

- ✅ User Registration & Login (JWT-based Authentication)
- 🔒 Secure Password Hashing using BCrypt
- 💸 Deposit & Withdraw Money
- 📜 View Transaction History
- 🗂️ Persistent data management with Spring Data JPA & Hibernate

---

## 🛠️ Tech Stack

| Technology       | Purpose                          |
|------------------|----------------------------------|
| Spring Boot       | Core application framework       |
| Spring Security   | Authentication and authorization |
| JWT               | Stateless user authentication    |
| Spring Data JPA   | ORM layer                        |
| Hibernate         | JPA implementation               |
| MySQL             | Relational database              |
| Maven             | Project management               |
| Postman           | API testing                      |

---

## 🔐 Authentication Flow

1. **Register** a new user via `/bank/new`
2. **Login** via `/bank/login` → receive JWT token
3. Include JWT in `Authorization` header for all secured endpoints:


---

## 📬 API Endpoints

### 🔑 Authentication

- `POST /bank/new` — Register new user  
- `POST /bank/login` — Authenticate and receive JWT

### 💼 Banking Operations (Requires JWT)

- `GET /bank/balance` — View current balance  
- `POST /bank/deposit` — Deposit money  
- `POST /bank/withdraw` — Withdraw money  
- `GET /bank/transactions` — View all transactions  

---

## 🧪 Testing

Use **Postman** or any REST client to test the APIs.  
Make sure to include the JWT token after login in the Authorization header.

---

## 🧰 How to Run

1. Clone the repo:
```bash
git clone https://github.com/GOKUL03112004/BankingSimulation.git
cd BankingSimulation

mvn clean install
mvn spring-boot:run

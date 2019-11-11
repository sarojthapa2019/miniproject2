package ea.miniproject2.bankservice.service;


import ea.miniproject2.bankservice.model.Bank;
import ea.miniproject2.bankservice.model.Orders;
import org.springframework.http.ResponseEntity;

public interface BankService {
    Bank saveBank(Bank bank);
    public ResponseEntity<Orders> getOrder(String token);
}

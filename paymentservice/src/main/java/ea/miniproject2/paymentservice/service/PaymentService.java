package ea.miniproject2.paymentservice.service;

import ea.miniproject2.paymentservice.model.Orders;
import ea.miniproject2.paymentservice.model.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment savePayment(Payment payment);
    List<Payment> findAllPayment();
    Optional<Payment> findById(Long id);
    public ResponseEntity<Orders> getOrder(String token);
}

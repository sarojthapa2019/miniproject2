package ea.miniproject2.paymentservice.service;

import ea.miniproject2.paymentservice.model.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment savePayment(Payment payment);
    List<Payment> findAllPayment();
    Optional<Payment> findById(Long id);

    ResponseEntity<String> callBankService(String token, Object object, String mode);
    ResponseEntity<String> callPaypalService(String token, Object object, String mode);
    ResponseEntity<String> callCreditCardService(String token, Object object, String mode);
}

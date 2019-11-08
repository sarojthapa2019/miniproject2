package ea.miniproject2.creditcardservice.service;


import ea.miniproject2.creditcardservice.model.CreditCard;
import ea.miniproject2.creditcardservice.model.Orders;
import org.springframework.http.ResponseEntity;

public interface CreditCardService {

    CreditCard saveCreditCard(CreditCard creditCard);
    public ResponseEntity<Orders> getOrder(String token);
}

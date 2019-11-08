package ea.miniproject2.paypalservice.service;


import ea.miniproject2.paypalservice.model.Orders;
import ea.miniproject2.paypalservice.model.Paypal;
import org.springframework.http.ResponseEntity;

public interface PaypalService {
    Paypal savePaypal(Paypal paypal);
    public ResponseEntity<Orders> getOrder(String token);
}

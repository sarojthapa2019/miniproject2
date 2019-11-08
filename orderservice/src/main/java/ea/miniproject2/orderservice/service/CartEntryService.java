package ea.miniproject2.orderservice.service;

import ea.miniproject2.orderservice.model.CartEntry;
import ea.miniproject2.orderservice.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartEntryService {
    CartEntry saveCart(CartEntry cartEntry, String token);
    List<CartEntry> findAll();
    ResponseEntity<Product> getProductById(String token, Long id);
    List<CartEntry> getCartEntryByUsername(String username);
}

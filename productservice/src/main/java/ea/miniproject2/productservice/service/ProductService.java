package ea.miniproject2.productservice.service;


import ea.miniproject2.productservice.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Optional<Product> findById(Long id);
    Product updateQuantity(HashMap<Long, Integer> cart);
}

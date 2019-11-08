package ea.miniproject2.productservice.service.serviceimpl;

import ea.miniproject2.productservice.model.Product;
import ea.miniproject2.productservice.repository.ProductRepository;
import ea.miniproject2.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateQuantity(HashMap<Long, Integer> cart) {

        for(Map.Entry<Long, Integer> entry: cart.entrySet()){
            Product p = productRepository.findById(entry.getKey()).get();
            if(p.getQuantity()< entry.getValue()){
                return p;

            }
            p.setQuantity(p.getQuantity()-entry.getValue());
            Product updated = productRepository.save(p);
        }
        return null;
    }
}

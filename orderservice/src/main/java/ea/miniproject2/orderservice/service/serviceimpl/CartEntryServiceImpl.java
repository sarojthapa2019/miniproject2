package ea.miniproject2.orderservice.service.serviceimpl;

import ea.miniproject2.orderservice.model.CartEntry;
import ea.miniproject2.orderservice.model.Product;
import ea.miniproject2.orderservice.repository.CartEntryRepository;
import ea.miniproject2.orderservice.service.CartEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartEntryServiceImpl implements CartEntryService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CartEntryRepository cartEntryRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${PRODUCT_SERVICE:#{null}}")

    private String producturl;
    @Override
    public CartEntry saveCart(CartEntry cartEntry, String token) {
        Long productId = cartEntry.getProductId();
        Product product = getProductById(token, productId).getBody();
        cartEntry.setCartTotalPrice(cartEntry.getQuantity() * product.getRate());
        return cartEntryRepository.save(cartEntry);
    }

    @Override
    public List<CartEntry> findAll() {
        return cartEntryRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> getProductById(String token, Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token );
        ResponseEntity<Product> response = null;
        final String uri = String.format("http://%s/product/{id}",producturl);
        try{
            response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>("parameters", headers),
                    new ParameterizedTypeReference<Product>() {
                    },id);
        }
        catch (Exception x){
            x.printStackTrace();
        }
        finally {
            return response;
        }


    }

    @Override
    public List<CartEntry> getCartEntryByUsername(String username) {
        return cartEntryRepository.getByUsername(username);
    }
}

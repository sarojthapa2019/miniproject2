package ea.miniproject2.orderservice.service.serviceimpl;

import ea.miniproject2.orderservice.model.CartEntry;
import ea.miniproject2.orderservice.model.Orders;
import ea.miniproject2.orderservice.model.Product;
import ea.miniproject2.orderservice.repository.CartEntryRepository;
import ea.miniproject2.orderservice.repository.OrderRepository;
import ea.miniproject2.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Orders saveOrder(Orders orders, String token) {
        List<CartEntry> cartEntries = orders.getCartEntries();
        HashMap<Long, Integer> productIdList = new HashMap<>();
        double sum = 0D;
        for(CartEntry cartEntry: cartEntries){
            productIdList.put(cartEntry.getProductId(), cartEntry.getQuantity());
            cartEntry.setOrders(orders);
            sum += cartEntry.getCartTotalPrice();
        }
        orders.setTotal(sum);
        String response = updateProductQuantity(token, productIdList).getBody();
        if(response.equals("success")){

            return orderRepository.save(orders);

        }
        return null;
    }

    @Override
    public Optional<Orders> getOrderById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Orders> getAllOrdersByUser(String username) {
        return null;
    }

    @Override
    public Optional<Orders> findByUsername(String username) {
        return orderRepository.findFirstByUsernameOrderByIdDesc(username);
    }

    public ResponseEntity<String> updateProductQuantity(String token, HashMap<Long, Integer> productIdList){


        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("Authorization", "Bearer "+token );
//        requestBody.add("productIdList", String.valueOf(productIdList));
        requestBody.add("Content-Type", "application/json");

        HttpEntity<HashMap<Long, Integer>> formEntity = new HttpEntity<HashMap<Long, Integer>>(productIdList, requestBody);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/product/updatequantity", HttpMethod.POST, formEntity,
                new ParameterizedTypeReference<String>() {
                });
        return response;
    }
}

package ea.miniproject2.paypalservice.service.serviceimpl;


import ea.miniproject2.paypalservice.model.Orders;
import ea.miniproject2.paypalservice.model.Paypal;
import ea.miniproject2.paypalservice.repository.PaypalRepository;
import ea.miniproject2.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private PaypalRepository paypalRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Paypal savePaypal(Paypal paypal) {
        return paypalRepository.save(paypal);
    }
    @Override
    public ResponseEntity<Orders> getOrder(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token );
        ResponseEntity<Orders> response = restTemplate.exchange("http://localhost:8082/getorder/{id}", HttpMethod.GET, new HttpEntity<>("parameters", headers),
                new ParameterizedTypeReference<Orders>() {
                },1);
        return response;
    }
}

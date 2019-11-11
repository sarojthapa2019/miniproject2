package ea.miniproject2.paypalservice.service.serviceimpl;


import ea.miniproject2.paypalservice.model.Orders;
import ea.miniproject2.paypalservice.model.Paypal;
import ea.miniproject2.paypalservice.repository.PaypalRepository;
import ea.miniproject2.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${ORDER_SERVICE:#{null}}")
    private String orderUrl;
    @Override
    public Paypal savePaypal(Paypal paypal) {
        return paypalRepository.save(paypal);
    }
    @Override
    public ResponseEntity<Orders> getOrder(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token );
        final String uri = String.format("http://%s/getorder/{id}", orderUrl);
        ResponseEntity<Orders> response = null;
        try{
            response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>("parameters", headers),
                    new ParameterizedTypeReference<Orders>() {
                    },1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return response;
        }
    }
}

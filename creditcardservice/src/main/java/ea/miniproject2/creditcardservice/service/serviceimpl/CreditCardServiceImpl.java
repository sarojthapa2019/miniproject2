package ea.miniproject2.creditcardservice.service.serviceimpl;


import ea.miniproject2.creditcardservice.model.CreditCard;
import ea.miniproject2.creditcardservice.model.Orders;
import ea.miniproject2.creditcardservice.repository.CreditCardRepository;
import ea.miniproject2.creditcardservice.service.CreditCardService;
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
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${ORDER_SERVICE:#{null}}")
    private String orderUrl;

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public ResponseEntity<Orders> getOrder(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        final String uri = String.format("http://%s/getorder/{id}", orderUrl);
        ResponseEntity<Orders> response = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>("parameters", headers),
                    new ParameterizedTypeReference<Orders>() {
                    }, 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return response;
        }

    }
}

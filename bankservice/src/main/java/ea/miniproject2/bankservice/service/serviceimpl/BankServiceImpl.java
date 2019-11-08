package ea.miniproject2.bankservice.service.serviceimpl;


import ea.miniproject2.bankservice.model.Bank;
import ea.miniproject2.bankservice.model.Orders;
import ea.miniproject2.bankservice.repository.BankRepository;
import ea.miniproject2.bankservice.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
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

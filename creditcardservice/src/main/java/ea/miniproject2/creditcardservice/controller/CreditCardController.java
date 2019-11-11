package ea.miniproject2.creditcardservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import ea.miniproject2.creditcardservice.model.CreditCard;
import ea.miniproject2.creditcardservice.model.Orders;
import ea.miniproject2.creditcardservice.service.CreditCardService;
import ea.miniproject2.creditcardservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
public class CreditCardController {
    @Autowired
    private TokenDecoderService tokenDecoderService;

    @Autowired
    private CreditCardService creditCardService;



    @PostMapping("/payment/cc")
    public String paymentModeCC(@RequestHeader(name="Authorization") String token, @RequestHeader(name="secret") String secret, @RequestBody CreditCard cc) throws UnsupportedEncodingException, JsonProcessingException {
        if(secret.equals("cc_secret_key")) {
            HashMap<String, String> dataHash = tokenDecoderService.decode(token);
            Orders orders = null;
            if (dataHash.get("role").equals("ROLE_USER")) {
                orders = creditCardService.getOrder(token).getBody();
                return "Payment processed for Order Id" + orders.getId() + "with amount " + orders.getTotal();
            }
        }
        return "Sorry cannot make payment";

    }
}

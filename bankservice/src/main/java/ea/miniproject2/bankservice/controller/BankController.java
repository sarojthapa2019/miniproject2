package ea.miniproject2.bankservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.bankservice.model.Bank;
import ea.miniproject2.bankservice.model.Orders;
import ea.miniproject2.bankservice.service.BankService;
import ea.miniproject2.bankservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
public class BankController {
    @Autowired
    private TokenDecoderService tokenDecoderService;

    @Autowired
    private BankService bankService;


    @PostMapping("/payment/bank")
    public String paymentModeBank(@RequestHeader(name="Authorization") String token, @RequestHeader(name="secret") String secret,   @RequestBody Bank bank) throws UnsupportedEncodingException, JsonProcessingException {
       if(secret.equals("bank_secret_key")) {
           HashMap<String, String> dataHash = tokenDecoderService.decode(token);
           Orders orders = null;
           if (dataHash.get("role").equals("ROLE_USER")) {

               orders = bankService.getOrder(token).getBody();
               return "Payment processed for Order Id " + orders.getId() + " with amount " + orders.getTotal();
           }
       }
        return "Sorry cannot make payment";

    }
}

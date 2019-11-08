package ea.miniproject2.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.orderservice.model.CartEntry;
import ea.miniproject2.orderservice.model.Orders;
import ea.miniproject2.orderservice.model.Product;
import ea.miniproject2.orderservice.repository.CartEntryRepository;
import ea.miniproject2.orderservice.service.CartEntryService;
import ea.miniproject2.orderservice.service.OrderService;
import ea.miniproject2.orderservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TokenDecoderService tokenDecoderService;
    @Autowired
    private CartEntryService cartEntryService;

    @PostMapping("/order")
    public Orders saveOrder(@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException, JsonProcessingException {
       Orders orders1 = new Orders();
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
            orders1.setUsername(dataHash.get("sub"));
            orders1.setCartEntries(cartEntryService.getCartEntryByUsername(dataHash.get("sub")));
            orders1 =  orderService.saveOrder(orders1, token);

        }
        return orders1;
    }
    @GetMapping(value = "/getorder/{id}")
    public Orders getOrder(@PathVariable long id, @RequestHeader (name="Authorization") String token ) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
            System.out.println("=========================");

            return orderService.findByUsername(dataHash.get("sub")).get();
        }
        return null;
    }

}

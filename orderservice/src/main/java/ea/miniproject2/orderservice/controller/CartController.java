package ea.miniproject2.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.miniproject2.orderservice.model.CartEntry;
import ea.miniproject2.orderservice.service.CartEntryService;
import ea.miniproject2.orderservice.service.serviceimpl.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private TokenDecoderService tokenDecoderService;
    @Autowired
    private CartEntryService cartEntryService;
    @PostMapping("/cart/save")
    public List<CartEntry> saveCart(@RequestHeader (name = "Authorization") String token, @RequestBody CartEntry cartEntry) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
            cartEntry.setUsername(dataHash.get("sub"));
           cartEntryService.saveCart(cartEntry, token);
        }
    return cartEntryService.findAll();
    }

}

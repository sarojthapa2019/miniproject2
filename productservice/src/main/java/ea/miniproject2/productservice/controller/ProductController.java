package ea.miniproject2.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ea.miniproject2.productservice.model.Product;
import ea.miniproject2.productservice.service.ProductService;
import ea.miniproject2.productservice.service.TokenDecoderService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private ProductService productService;
    @Autowired
    private TokenDecoderService tokenDecoderService;
    @GetMapping(value = "/product/{id}")
    public Product get(@PathVariable long id, @RequestHeader (name="Authorization") String token ) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
            System.out.println("=========================");
            System.out.println(productService.findById(id));
            return productService.findById(id).get();
        }
        return null;
    }
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAll(@RequestHeader (name="Authorization") String token) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")){
            return productService.findAllProducts();
        }
        return null;
    }

    @PostMapping(value = "/product/updatequantity")
    public String updateQuantity(@RequestHeader Map<String, String> header, @RequestBody HashMap<Long, Integer> cart) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode( header.get("authorization"));
        if(dataHash.get("role").equals("ROLE_USER")){
            Product p = productService.updateQuantity(cart);
          if (p ==null){
              return "success";
          }
          return p.getName()+" doesn't have enough quantity...Will contact vendor soon";

        }
        return "unauthorized person";
    }

}

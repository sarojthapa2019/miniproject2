package ea.miniproject2.shippingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShippingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShippingserviceApplication.class, args);
    }

}

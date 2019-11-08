package ea.miniproject2.orderservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String vendor;
    private Integer quantity;
    private Double rate;
}

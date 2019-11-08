package ea.miniproject2.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long productId;
    private Integer quantity;
    private Double  cartTotalPrice;
    @ManyToOne
    @JsonIgnore
    private Orders orders;


}

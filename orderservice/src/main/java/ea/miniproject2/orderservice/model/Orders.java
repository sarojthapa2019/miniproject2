package ea.miniproject2.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    @OneToMany(mappedBy = "orders")
    private List<CartEntry> cartEntries = new ArrayList<>();
    private boolean boughtFlag = false;

    @CreationTimestamp
    private LocalDate orderDate;
    @UpdateTimestamp
    private LocalDate updatedDate;

    private Double total;

//    @Override
//    public String toString() {
//        return "Orders{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", cartEntries=" + cartEntries +
//                ", boughtFlag=" + boughtFlag +
//                ", orderDate=" + orderDate +
//                ", updatedDate=" + updatedDate +
//                ", total=" + total +
//
//                '}';
//    }



}

package ea.miniproject2.paymentservice.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Orders {
    private Long id;

    private String username;
    private List<Object> cartEntries = new ArrayList<>();
    private boolean boughtFlag = false;
    private LocalDate orderDate;
    private LocalDate updatedDate;
    private Double total;
}

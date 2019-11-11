package ea.miniproject2.creditcardservice.model;

import lombok.Data;

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

package uz.developers.appwarehouse.entity.warehouseEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity(name = "Users")
public class User  {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String  firtName;
    @Column(nullable = false)
    private String  lastName;
    @Column(unique = true)
    private String  phoneNumber;
    @Column(nullable = false, unique = true)
    private String code;
    private boolean active=true;

    @ManyToMany
    private List<Warehouse> warehouses;





}

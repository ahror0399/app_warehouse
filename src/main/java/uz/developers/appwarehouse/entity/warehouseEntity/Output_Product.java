package uz.developers.appwarehouse.entity.warehouseEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Output_Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private     Product product;

    @Column(nullable = false)
    private Double amount;

    private Double price;



    @ManyToOne
    private Output output;

}
